package uk.co.sangharsh.ws.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.co.sangharsh.service.SlackClient;

@Component
@Path(SlackResource.ROOT)
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes(MediaType.APPLICATION_JSON)
public class SlackResource {
	public static final String ROOT = "slack";
	
	@Autowired
	private SlackClient slackClient;
	
	@GET
	@Path("authorize/handle")
	public Response redirected(@QueryParam(value = "code") String code){
		System.out.println("CODE: "+code);
		String resp = slackClient.authorize(code);
		System.out.println(resp);
		return Response.ok(resp).build();
	}
	
	@POST
	@Path("command")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response commandPost(){
		System.out.println("Outgoing Webhooks pOST");
		return Response.ok().build();
	}
}
