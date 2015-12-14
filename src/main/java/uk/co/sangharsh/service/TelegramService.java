package uk.co.sangharsh.service;

import java.util.List;

import org.telegram.client.pojo.Result;
import org.telegram.client.pojo.SendableImage;
import org.telegram.client.pojo.SendableText;
import org.telegram.client.pojo.Telegram;
import org.telegram.client.pojo.Update;
import org.telegram.client.pojo.User;
import org.telegram.client.type.ChatAction;

public interface TelegramService {

	Result<Telegram> message(String tgUserId, SendableText sendable);
	
	Result<Telegram> message(String tgUserId, SendableImage sendable);

	Result<Telegram> reply(Telegram telegram, SendableText sendable);

	Result<User> getMe();

	Result<List<Update>> getUpdates();

	Result<Boolean> setStatus(Telegram telegram, ChatAction action);

	Telegram photo(String tgUserId, String url);

}
