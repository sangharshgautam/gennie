package uk.co.sangharsh.ws.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.client.pojo.GetMeResult;
import org.telegram.client.pojo.User;

import uk.co.sangharsh.service.TelegramService;

@Component
@Path(TelegramResource.ROOT)
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class TelegramResource {
	
	public static final String GET_ME = "getMe";

	public static final String ROOT = "telegram";

	
	
	@Autowired
	private TelegramService telegramService;
	
	@GET
	@Path(GET_ME)
	public User getMe(){
		GetMeResult result = telegramService.getMe();
		return result.getResult();
	}
}
