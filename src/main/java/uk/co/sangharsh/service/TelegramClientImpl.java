package uk.co.sangharsh.service;

import static org.glassfish.jersey.media.multipart.FormDataContentDisposition.name;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.client.method.Method;
import org.telegram.client.param.Param;
import org.telegram.client.pojo.GetMeResult;
import org.telegram.client.pojo.GetUpdatesResult;
import org.telegram.client.pojo.MessageResult;
import org.telegram.client.pojo.Result;
import org.telegram.client.pojo.Telegram;
import org.telegram.client.pojo.Update;
import org.telegram.client.pojo.User;
import org.telegram.client.pojo.UserProfilePhotos;

import uk.co.sangharsh.client.commons.pojo.Sendable;
import uk.co.sangharsh.ws.resource.AdminResource;

@Service
public class TelegramClientImpl implements TelegramClient {
	
	private static final Logger LOGGER = Logger.getLogger(TelegramClientImpl.class.getName());
	
	@Value("${telegram.bot.token}")
	private String token;
	
	@Value("${telegram.base.url}")
	private String telegramBaseUrl;
	
	
	private Client client; 
	
	@PostConstruct
	public void setClientProperties(){
		ClientConfig cc = new ClientConfig()
				.property(UnmarshallerProperties.JSON_ATTRIBUTE_PREFIX, "@")
				.property(MarshallerProperties.JSON_ATTRIBUTE_PREFIX, "@")
				.register(MoxyJsonFeature.class)
				.register(new LoggingFilter(LOGGER, true))
				.register(MultiPartFeature.class);
		this.client = ClientBuilder.newClient(cc);
	}
	
	private WebTarget webTarget() {
		return client.target(telegramBaseUrl+token);
	}
	
	public Result<User> getMe() {
		return Method.getMe.get(webTarget(), new GenericType<GetMeResult>() {});
	}
	
	@Override
	public Result<Telegram> sendMessage(final String chatId, final String replyToId, Sendable sendable) {
		Form form = new Form()
			.param(Param.CHAT_ID.getVal(),chatId)
			.param(Param.TEXT.getVal(), sendable.inLine())
			;
		if(StringUtils.isNotBlank(replyToId)){
			form.param(Param.REPLY_TO_MESSAGE_ID.getVal(), replyToId);
		}
		return Method.sendMessage.post(webTarget(), MessageResult.class, Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
	}

	public MessageResult forwardMessage(final String chatId, String messageId, Sendable sendable) {
		Form form = new Form()
			.param(Param.CHAT_ID.getVal(),chatId)
			.param(Param.FROM_CHAT_ID.getVal(),AdminResource.ADMIN_CHAT_ID)
			.param(Param.MESSAGE_ID.getVal(), messageId)
			.param(Param.TEXT.getVal(), sendable.inLine())
			;
		return Method.forwardMessage.post(webTarget(), MessageResult.class, Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
	}

	public Result<Telegram> sendPhoto(final Telegram telegram, File file) {
		final String chatId = telegram.chat().getIdAsString();
		FileDataBodyPart fileDataBodyPart = new FileDataBodyPart(Param.PHOTO.getVal(), file);
		fileDataBodyPart.setContentDisposition(name(Param.PHOTO.getVal()).fileName(file.getName()).build());

		final MultiPart multiPartEntity = new FormDataMultiPart()
			.field(Param.CHAT_ID.getVal(),chatId)
//			.field(Param.REPLY_MARKUP.getVal(), ReplyKeyboardMarkup.getReplyMarkup(TeleCommand.GIPHY).asJson())
			.bodyPart(fileDataBodyPart);
		
		return Method.sendPhoto.post(webTarget(), MessageResult.class, Entity.entity(multiPartEntity, MediaType.MULTIPART_FORM_DATA));
	}

	public Result<Telegram> sendAudio(final Telegram telegram, File file) {
		final String chatId = telegram.chat().getIdAsString();
		FileDataBodyPart fileDataBodyPart = new FileDataBodyPart(Param.PHOTO.getVal(), file);
		fileDataBodyPart.setContentDisposition(FormDataContentDisposition.name(Param.AUDIO.getVal()).fileName(file.getName()).build());

		final MultiPart multiPartEntity = new FormDataMultiPart()
			.field(Param.CHAT_ID.getVal(), chatId)
			.field(Param.DURATION.getVal(), "10") //Duration of the audio in seconds
			.field(Param.PERFORMER.getVal(), "Sonu Nigam") //Performer
			.field(Param.TITLE.getVal(), "Track name")//Track name
//			.field(Param.REPLY_TO_MESSAGE_ID, value)
//			.field(Param.REPLY_MARKUP.getVal(), ReplyKeyboardMarkup.getReplyMarkup(TeleCommand.GIPHY).asJson())
			.bodyPart(fileDataBodyPart);
		return Method.sendAudio.post(webTarget(), MessageResult.class, Entity.entity(multiPartEntity, MediaType.MULTIPART_FORM_DATA));
	}

	public Result<Telegram> sendDocument() {
		return Method.sendDocument.get(webTarget(), new GenericType<MessageResult>() {});
	}

	public Result<Telegram> sendSticker() {
		return Method.sendSticker.get(webTarget(), new GenericType<MessageResult>() {});
	}

	public Result<Telegram> sendVideo() {
		return Method.sendVideo.get(webTarget(), new GenericType<MessageResult>() {});
	}

	public Result<Telegram> sendLocation() {
		return Method.sendLocation.get(webTarget(), new GenericType<MessageResult>() {});
	}

	/*public TelegramWrapper sendChatAction(final Update update, final BotMessage action) {
		final String chatId = update.getMessage().chat().getIdAsString();
		return Method.sendChatAction.get(webTarget(), TelegramWrapper.class, new HashMap<Param, String>(){{
			put(Param.CHAT_ID, chatId);
			put(Param.ACTION, action.inLine());
		}});
		
	}*/

	public UserProfilePhotos getUserProfilePhotos(Update update) {
		final String chatId = update.getMessage().chat().getIdAsString();
		return Method.getUserProfilePhotos.get(webTarget(), UserProfilePhotos.class, new HashMap<Param, String>(){{
			put(Param.CHAT_ID, chatId);
//			put(Param.LIMIT, "2");
		}});
	}

	public Result<List<Update>> getUpdates() {
		return Method.getUpdates.get(webTarget(), new GenericType<GetUpdatesResult>() {});
	}

	/**
	 * Notes
	 * 1. You will not be able to receive updates using getUpdates for as long as an outgoing webhook is set up.
	 * 2. We currently do not support self-signed certificates.
	 * 3. Ports currently supported for Webhooks: 443, 80, 88, 8443.
	 * @param url HTTPS url to send updates to. Use an empty string to remove webhook integration
	 * @throws MalformedURLException 
	 */
	public void setWebhook(final String webhook) throws MalformedURLException {
		HashMap<Param, String> params = new HashMap<Param, String>() {{
			put(Param.URL, webhook);
		}};
		if(StringUtils.isNotBlank(webhook)){
			URL url = new URL(webhook);
			if(validateProtocol(url) && validatePort(url)){
				Method.setWebhook.get(webTarget(), Void.class, params);
			}else{
				System.out.println("Invalid Webhook value");
			}
		}else{
			Method.setWebhook.get(webTarget(), Void.class, params);
		}
		
	}

	private boolean validateProtocol(URL url) {
		return url.getProtocol().equalsIgnoreCase("https");
	}

	private boolean validatePort(URL url) {
		int[] validPorts = new int[]{443, 80, 88, 8443};
		return Arrays.asList(validPorts).contains(url.getPort());
	}
}