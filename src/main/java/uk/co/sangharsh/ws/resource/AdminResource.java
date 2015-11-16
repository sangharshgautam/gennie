package uk.co.sangharsh.ws.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.client.pojo.MessageResult;
import org.telegram.client.pojo.Telegram;

import uk.co.sangharsh.service.TelegramService;

@Component
@Path(AdminResource.ROOT)
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes(MediaType.APPLICATION_JSON)
public class AdminResource {
	public static final String ROOT = "admin";
	private static final String NOTIFY = "notify";

	final String ADMIN_CHAT_ID = "120340564";

	@Autowired
	private TelegramService telegramService;

	@GET
	@Path("message/{chatId}")
	public Telegram getMe(@PathParam("chatId") String chatId, @QueryParam("msg") String message) {
		MessageResult result = telegramService.message(chatId, message);
		return result.getResult();
	}

	@GET
	@Path(NOTIFY)
	public Telegram getMe(@QueryParam("msg") String message) {
		MessageResult result = telegramService.message(ADMIN_CHAT_ID, message);
		return result.getResult();
	}
}
