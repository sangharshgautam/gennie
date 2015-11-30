package uk.co.sangharsh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.client.pojo.Result;
import org.telegram.client.pojo.Telegram;
import org.telegram.client.pojo.Update;
import org.telegram.client.pojo.User;
import org.telegram.client.type.ChatAction;

import uk.co.sangharsh.client.commons.pojo.Sendable;
import uk.co.sangharsh.dao.TelegramDao;

@Service
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
	@Transactional
	public Result<Telegram> message(String chatId, Sendable sendable) {
		String telegramId = null;
		return message(chatId, sendable, telegramId);
	}

	@Override
	public Result<Telegram> reply(Telegram telegram, Sendable sendable) {
		String chatId = telegram.chat().getIdAsString();
		String telegramId = telegram.getIdAsString();
		return message(chatId, sendable, telegramId);
	}
	
	private Result<Telegram> message(String chatId, Sendable sendable, String telegramId) {
		Result<Telegram> response = telegramClient.sendMessage(chatId, telegramId , sendable);
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
	
}
