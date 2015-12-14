package uk.co.sangharsh.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.client.pojo.ReplyKeyboardMarkup;
import org.telegram.client.pojo.Result;
import org.telegram.client.pojo.SendableImage;
import org.telegram.client.pojo.SendableText;
import org.telegram.client.pojo.Telegram;
import org.telegram.client.pojo.Update;
import org.telegram.client.pojo.User;
import org.telegram.client.type.ChatAction;

import uk.co.sangharsh.dao.TelegramDao;

@Service
@Transactional
public class TelegramServiceImpl implements TelegramService {

	@Autowired
	private TelegramClient telegramClient;
	
	@Autowired
	private TelegramDao telegramDao; 
	
	@Override
	public Result<User> getMe() {
		return telegramClient.getMe();
	}

	@Override
	public Result<Telegram> message(String chatId, SendableText sendable) {
		System.out.println("SendableText");
		String telegramId = null;
		return message(chatId, sendable, telegramId, sendable.markup());
	}
	@Override
	public Result<Telegram> message(String chatId, SendableImage sendable) {
		System.out.println("SendableImage");
		Result<Telegram> response = telegramClient.sendPhoto(chatId, sendable.caption(), sendable.file(), sendable.markup());
		if(response.isOk()){
			telegramDao.create(response.getResult());
		}
		return response;
	}
	@Override
	public Result<Telegram> reply(Telegram telegram, SendableText sendable) {
		String chatId = telegram.chat().getIdAsString();
		String telegramId = telegram.getIdAsString();
		return message(chatId, sendable, telegramId, sendable.markup());
	}
	
	private Result<Telegram> message(String chatId, SendableText sendable, String telegramId, ReplyKeyboardMarkup markup) {
		
		Result<Telegram> response = telegramClient.sendMessage(chatId, telegramId , sendable, markup);
		if(response.isOk()){
			telegramDao.create(response.getResult());
		}
		return response;
	}
	
	@Override
	public Result<List<Update>> getUpdates() {
		return telegramClient.getUpdates();
	}

	@Override
	public Result<Boolean> setStatus(Telegram telegram, ChatAction action) {
		return telegramClient.sendChatAction(telegram, action);
	}

	@Override
	public Telegram photo(String tgUserId, String url) {
		try {
//			File file = FileUtils.toFile(new URL(url));
			File file = HttpDownloadUtility.downloadFile(url);
			Result<Telegram> response = telegramClient.sendPhoto(tgUserId, url, file, null);
			if(response.isOk()){
				telegramDao.create(response.getResult());
				List<Telegram> replies = new ArrayList<Telegram>();
				int count =0;
				do {
					replies = telegramDao.findByReplyTo(response.getResult().getId());
					if(!replies.isEmpty()){
						return replies.get(0);
					}
					Thread.currentThread().sleep(3000);
					count++;	
				} while (replies.isEmpty() || count >15);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
