package uk.co.sangharsh.service;

import java.util.List;

import org.telegram.client.pojo.ReplyKeyboardMarkup;
import org.telegram.client.pojo.Result;
import org.telegram.client.pojo.SendableImage;
import org.telegram.client.pojo.SendableText;
import org.telegram.client.pojo.Telegram;
import org.telegram.client.pojo.Update;
import org.telegram.client.pojo.User;
import org.telegram.client.type.ChatAction;

public interface TelegramService {

	Result<Telegram> message(String tgUserId, SendableText sendable, ReplyKeyboardMarkup markup);
	
	Result<Telegram> message(String tgUserId, SendableImage sendable, ReplyKeyboardMarkup markup);

	Result<Telegram> reply(Telegram telegram, SendableText sendable, ReplyKeyboardMarkup markup);

	Result<User> getMe();

	Result<List<Update>> getUpdates();

	Result<Boolean> setStatus(Telegram telegram, ChatAction action);

	Telegram photo(String tgUserId, String url);

}
