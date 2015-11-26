package uk.co.sangharsh.service;

import java.net.MalformedURLException;

import org.telegram.client.pojo.GetMeResult;
import org.telegram.client.pojo.GetUpdatesResult;
import org.telegram.client.pojo.MessageResult;
import org.telegram.client.pojo.Telegram;
import org.telegram.client.pojo.Update;
import org.telegram.client.pojo.UserProfilePhotos;

import uk.co.sangharsh.client.commons.pojo.Sendable;

public interface TelegramService {
	GetMeResult getMe();
	MessageResult forwardMessage(String chatId, String messageId, String message);
//    TelegramWrapper sendPhoto(final Update update, File file);
    Telegram sendAudio();
    Telegram sendDocument();
    Telegram sendSticker();
    Telegram sendVideo();
    Telegram sendLocation();
//    TelegramWrapper sendChatAction(Update update, BotMessage action);
    UserProfilePhotos getUserProfilePhotos(Update update);
    GetUpdatesResult getUpdates();
    void setWebhook(final String webhook) throws MalformedURLException;
    MessageResult send(Update update, boolean addParentRef, Sendable sendable);
//    TelegramWrapper send(Update update, boolean addParentRef, Sendable sendable, ReplyKeyboard replyKeyboard);
    MessageResult message(String chatId, String message);
}
