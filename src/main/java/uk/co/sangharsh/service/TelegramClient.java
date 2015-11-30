package uk.co.sangharsh.service;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

import org.telegram.client.pojo.Result;
import org.telegram.client.pojo.Telegram;
import org.telegram.client.pojo.Update;
import org.telegram.client.pojo.User;
import org.telegram.client.pojo.UserProfilePhotos;
import org.telegram.client.type.ChatAction;

import uk.co.sangharsh.client.commons.pojo.Sendable;

public interface TelegramClient {
	Result<User> getMe();
	Result<Telegram> forwardMessage(String chatId, String messageId, Sendable sendable);
	Result<Telegram> sendPhoto(final Telegram telegram, File file);
	Result<Telegram> sendAudio(final Telegram telegram, File file);
	Result<Telegram> sendDocument();
	Result<Telegram> sendSticker();
	Result<Telegram> sendVideo();
	Result<Telegram> sendLocation();
	Result<Boolean> sendChatAction(final Telegram telegram, ChatAction action);
	Result<UserProfilePhotos> getUserProfilePhotos(Update update);
    Result<List<Update>> getUpdates();
    void setWebhook(final String webhook) throws MalformedURLException;
    Result<Telegram> sendMessage(final String chatId, final String replyToId, Sendable sendable);
}
