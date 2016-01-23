package uk.co.sangharsh.ws.resource;

import static javax.ws.rs.core.Response.status;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.OK;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.client.pojo.Result;
import org.telegram.client.pojo.Update;

import uk.co.sangharsh.service.TelegramService;
import uk.co.sangharsh.service.UpdateProcessService;

@Component
@Path(TelegramUpdateResource.ROOT)
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
public class TelegramUpdateResource {
	
	public static final String ROOT = "telegram/updates";
	
	public static final String FETCH_UPDATES = "fetch";
	
	public static final String PULL_UPDATES = "pull";
	
	public static final String PROCESS_AUTO = "process/auto";

	private static final String PROCESS_MANUAL = "process/manual";
	
	@Autowired
	private TelegramService telegramService;
	
	@Autowired
	private UpdateProcessService updateProcessService; 
	
	@GET
	@Path(FETCH_UPDATES)
	public List<Update> getUpdated(){
		Result<List<Update>> result = telegramService.getUpdates();
		return result.getResult();
	}
	
	@GET
	@Path(PULL_UPDATES)
	public Response pullUpdates(){
		updateProcessService.pullUpdates();
		return Response.ok().build();
	}
	
	@GET
	@Path(PROCESS_MANUAL)
	public Response processManual(){
		try {
			updateProcessService.process();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.ok().build();
	}
	@POST
	@Path(PROCESS_AUTO)
	public Response processAuto(Update update){
		updateProcessService.push(update);
		try {
			updateProcessService.process();
		} catch (Exception e) {
			e.printStackTrace();
			return status(INTERNAL_SERVER_ERROR).build();
		}
		return status(OK).build();
	}
}
