package com.slack.api.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.filter.LoggingFilter;

import com.slack.api.client.pojo.MessagePayload;

public class SlackbotTest {

	private static final String TOKEN = "xoxp-16665703089-16666379522-17162274754-f62382199b";

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient()
				.register(LoggingFilter.class)
				;
	 	

		/*String response = client.target("https://slack.com/api").path("api.test")
				.queryParam(Param.TOKEN, TOKEN)
				.request().get(String.class);
		System.out.println(response);*/
		
		String resp = client.target("https://hooks.slack.com/services/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").request(MediaType.APPLICATION_JSON).post(Entity.entity(MessagePayload.payload("Hi I am a bot"), MediaType.APPLICATION_JSON), String.class);
		System.out.println(resp);	
	}
	
}
