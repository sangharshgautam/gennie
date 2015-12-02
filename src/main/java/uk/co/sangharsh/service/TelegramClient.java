package uk.co.sangharsh.service;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

import org.telegram.client.pojo.ReplyKeyboard;
import org.telegram.client.pojo.Result;
import org.telegram.client.pojo.Telegram;
import org.telegram.client.pojo.TgFile;
import org.telegram.client.pojo.Update;
import org.telegram.client.pojo.User;
import org.telegram.client.pojo.UserProfilePhotos;
import org.telegram.client.type.ChatAction;

import uk.co.sangharsh.client.commons.pojo.Sendable;

public interface TelegramClient {
	Result<User> getMe();

	Result<Telegram> forwardMessage(String chatId, String messageId, Sendable sendable);

	Result<Telegram> sendPhoto(final String chatId, final File file);

	Result<Telegram> sendAudio(final Telegram telegram, final File file);

	Result<Telegram> sendDocument();

	Result<Telegram> sendSticker();

	Result<Telegram> sendVideo();

	Result<Telegram> sendLocation();

	/**
	 * Use this method when you need to tell the user that something is
	 * happening on the bot's side. The status is set for 5 seconds or less
	 * (when a message arrives from your bot, Telegram clients clear its typing
	 * status).
	 * 
	 * @param telegram
	 * @param action
	 * @return
	 */
	Result<Boolean> sendChatAction(final Telegram telegram, ChatAction action);

	/**
	 * Use this method to get a list of profile pictures for a user. Returns a
	 * UserProfilePhotos object.
	 * 
	 * @param tgUserId
	 *            Unique identifier of the target user
	 * @return
	 */
	Result<UserProfilePhotos> getUserProfilePhotos(final String tgUserId);

	Result<List<Update>> getUpdates();

	void setWebhook(final String webhook) throws MalformedURLException;

	Result<Telegram> sendMessage(final String chatId, final String replyToId, final Sendable sendable, final ReplyKeyboard keyboard);

	/**
	 * Use this method to get basic info about a file and prepare it for
	 * downloading. For the moment, bots can download files of up to 20MB in
	 * size. On success, a File object is returned. The file can then be
	 * downloaded via the link https://api.telegram.org/file/bot<token>/
	 * <file_path>, where <file_path> is taken from the response. It is
	 * guaranteed that the link will be valid for at least 1 hour. When the link
	 * expires, a new one can be requested by calling getFile again.
	 * 
	 * @param fileId
	 *            File identifier to get info about
	 * @return
	 */
	Result<TgFile> getFile(String fileId);
}
