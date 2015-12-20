package ai.wit;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.filter.LoggingFilter;

public class WitClientTest {

	public static final String TOKEN = "ANOA5GBKTF7J7G63WW5FRFM54SFIGKSI";
	public static void main(String[] args) {
		
		try {
			Client client = ClientBuilder.newClient().register(LoggingFilter.class);
			String response = client.target("https://api.wit.ai/message").queryParam("q", "summarize between 9 to 10").request().header("Authorization", "Bearer "+TOKEN).accept(MediaType.APPLICATION_JSON).get(String.class);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
