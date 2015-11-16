package uk.co.sangharsh.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
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
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.client.method.Method;
import org.telegram.client.param.Param;
import org.telegram.client.pojo.GetMeResult;
import org.telegram.client.pojo.GetUpdatesResult;
import org.telegram.client.pojo.Telegram;
import org.telegram.client.pojo.MessageResult;
import org.telegram.client.pojo.Update;
import org.telegram.client.pojo.UserProfilePhotos;

import uk.co.sangharsh.client.commons.pojo.Sendable;

@Service
public class TelegramServiceImpl implements TelegramService {
	
	private static final Logger LOGGER = Logger.getLogger(TelegramServiceImpl.class.getName());
	
	@Value("${telegram.bot.token}")
	private String token;
	
	@Value("${telegram.base.url}")
	private String telegramBaseUrl;
	
	
	private Client client; 
	
	@PostConstruct
	public void setClientProperties(){
		ClientConfig cc = new ClientConfig()
				.property(MarshallerProperties.JSON_ATTRIBUTE_PREFIX, "@")
				.register(MoxyJsonFeature.class)
				.register(new LoggingFilter(LOGGER, true))
				.register(MultiPartFeature.class);
		this.client = ClientBuilder.newClient(cc);
	}
	
	private WebTarget webTarget() {
		return client.target(telegramBaseUrl+token);
	}
	
	public GetMeResult getMe() {
		return Method.getMe.get(webTarget(), new GenericType<GetMeResult>() {});
	}

	public MessageResult send(final Update update, final boolean addParentRef, Sendable message) {
		return sendIn(update, addParentRef, message);
	}
	@Override
	public MessageResult message(final String chatId, String message) {
		Form form = new Form().param(Param.CHAT_ID.getVal(),chatId).param(Param.TEXT.getVal(), message);
		return Method.sendMessage.post(webTarget(), MessageResult.class, Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
	}
	public MessageResult sendIn(final Update update, final boolean addParentRef, Sendable message) {
		final String chatId = update.getMessage().chat().getIdAsString();
		Form form = new Form().param(Param.CHAT_ID.getVal(),chatId).param(Param.TEXT.getVal(), message.inLine());
		if(addParentRef){
			form = setParamToForm(Param.REPLY_TO_MESSAGE_ID, update.getMessage().getIdAsString(), form);
		}
		return Method.sendMessage.post(webTarget(), MessageResult.class, Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
	}

	private Form setParamToForm(Param param , String value, Form form) {
		return form.param(param.getVal(), value);
	}

	public Telegram forwardMessage() {
		return Method.forwardMessage.get(webTarget(), Telegram.class);
	}

	/*public TelegramWrapper sendPhoto(final Update update, File file) {
		final String chatId = update.getMessage().chat().getIdAsString();
		FileDataBodyPart fileDataBodyPart = new FileDataBodyPart(Param.PHOTO.getVal(), file);
		fileDataBodyPart.setContentDisposition(FormDataContentDisposition.name(Param.PHOTO.getVal()).fileName(file.getName()).build());

		final MultiPart multiPartEntity = new FormDataMultiPart().field(Param.CHAT_ID.getVal(),chatId).field(Param.REPLY_MARKUP.getVal(), ReplyKeyboardMarkup.getReplyMarkup(TeleCommand.GIPHY).asJson()).bodyPart(fileDataBodyPart);
		
		return Method.sendPhoto.post(webTarget(), TelegramWrapper.class, Entity.entity(multiPartEntity, MediaType.MULTIPART_FORM_DATA));
	}*/

	public Telegram sendAudio() {
		return Method.sendAudio.get(webTarget(), Telegram.class);
	}

	public Telegram sendDocument() {
		return Method.sendDocument.get(webTarget(), Telegram.class);
	}

	public Telegram sendSticker() {
		return Method.sendSticker.get(webTarget(), Telegram.class);
	}

	public Telegram sendVideo() {
		return Method.sendVideo.get(webTarget(), Telegram.class);
	}

	public Telegram sendLocation() {
		return Method.sendLocation.get(webTarget(), Telegram.class);
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

	public GetUpdatesResult getUpdates() {
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