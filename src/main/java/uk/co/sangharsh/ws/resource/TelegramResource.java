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
	public Response message(@PathParam("tgUserId") String tgUserId, @QueryParam("msg") String message){
		if(telegramService.message(tgUserId, message).isOk()){
			return status(OK).build();	
		}else{
			return status(INTERNAL_SERVER_ERROR).build();	
		}
			
	}
}
