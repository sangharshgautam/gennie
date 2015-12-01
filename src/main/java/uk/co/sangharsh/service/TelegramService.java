package uk.co.sangharsh.service;

import java.util.List;

import org.telegram.client.pojo.Result;
import org.telegram.client.pojo.SendableText;
import org.telegram.client.pojo.Telegram;
import org.telegram.client.pojo.Update;
import org.telegram.client.pojo.User;
import org.telegram.client.type.ChatAction;

import uk.co.sangharsh.client.commons.pojo.Sendable;

public interface TelegramService {

	Result<Telegram> message(String tgUserId, Sendable sendable);

	Result<Telegram> reply(Telegram telegram, Sendable sendable);

	Result<User> getMe();

	Result<List<Update>> getUpdates();

	Result<Boolean> setStatus(Telegram telegram, ChatAction action);

	Result<Telegram> photo(String tgUserId, String url);

}
