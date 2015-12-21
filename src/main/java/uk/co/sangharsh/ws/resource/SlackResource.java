package uk.co.sangharsh.ws.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Component
@Path(SlackResource.ROOT)
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes(MediaType.APPLICATION_JSON)
public class SlackResource {
	public static final String ROOT = "slack";
	
	@GET
	@Path("authorize/handle")
	public Response redirected(@QueryParam(value = "code") String code){
		System.out.println("CODE: "+code);
		return Response.ok().build();
	}
}
