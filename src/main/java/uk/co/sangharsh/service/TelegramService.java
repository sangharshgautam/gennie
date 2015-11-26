package uk.co.sangharsh.service;

import java.net.MalformedURLException;
import java.util.List;

import org.telegram.client.pojo.Result;
import org.telegram.client.pojo.Telegram;
import org.telegram.client.pojo.Update;
import org.telegram.client.pojo.User;
import org.telegram.client.pojo.UserProfilePhotos;

import uk.co.sangharsh.client.commons.pojo.Sendable;

public interface TelegramService {
	Result<User> getMe();
	Result<Telegram> forwardMessage(String chatId, String messageId, String message);
//    TelegramWrapper sendPhoto(final Update update, File file);
	Result<Telegram> sendAudio();
	Result<Telegram> sendDocument();
	Result<Telegram> sendSticker();
	Result<Telegram> sendVideo();
	Result<Telegram> sendLocation();
//    TelegramWrapper sendChatAction(Update update, BotMessage action);
    UserProfilePhotos getUserProfilePhotos(Update update);
    Result<List<Update>> getUpdates();
    void setWebhook(final String webhook) throws MalformedURLException;
    Result<Telegram> send(Update update, boolean addParentRef, Sendable sendable);
//    TelegramWrapper send(Update update, boolean addParentRef, Sendable sendable, ReplyKeyboard replyKeyboard);
    Result<Telegram> message(String chatId, String message);
}
