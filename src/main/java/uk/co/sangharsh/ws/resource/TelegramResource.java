package uk.co.sangharsh.ws.resource;

import static javax.ws.rs.core.Response.status;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.OK;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.client.pojo.Result;
import org.telegram.client.pojo.SendableText;
import org.telegram.client.pojo.Telegram;
import org.telegram.client.pojo.User;

import uk.co.sangharsh.service.TelegramService;

@Component
@Path(TelegramResource.ROOT)
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes(MediaType.APPLICATION_JSON)
public class TelegramResource {

	public static final String GET_ME = "getMe";

	public static final String ROOT = "telegram";

	@Autowired
	private TelegramService telegramService;

	@GET
	@Path(GET_ME)
	public User getMe() {
		Result<User> result = telegramService.getMe();
		return result.getResult();
	}
	
	@GET
	@Path("message/{tgUserId}")
	public Response sendMessage(@PathParam("tgUserId") String tgUserId, @QueryParam("msg") String message){
		Result<Telegram> response = telegramService.message(tgUserId, SendableText.create(message));
		if(response.isOk()){
			return status(OK).build();	
		}else{
			return status(INTERNAL_SERVER_ERROR).build();	
		}
			
	}
	@GET
	@Path("file/{tgUserId}")
	public Response sendFile(@PathParam("tgUserId") String tgUserId, @QueryParam("url") String url){
		Result<Telegram> response = telegramService.photo(tgUserId, url);
		if(response.isOk()){
			return status(OK).build();	
		}else{
			return status(INTERNAL_SERVER_ERROR).build();	
		}
			
	}
	
	@GET
	@Path("captcha/{tgUserId}")
	public Response captcha(@PathParam("tgUserId") String tgUserId, @QueryParam("challenge") String challenge){
		String url = new StringBuilder("http://www.google.com/recaptcha/api/image?c=").append(challenge).toString();
		Result<Telegram> response = telegramService.photo(tgUserId, url);
		if(response.isOk()){
			return status(OK).build();	
		}else{
			return status(INTERNAL_SERVER_ERROR).build();	
		}
			
	}
}
