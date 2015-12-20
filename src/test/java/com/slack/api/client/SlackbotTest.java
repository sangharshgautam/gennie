package com.slack.api.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.filter.LoggingFilter;

import com.slack.api.client.param.Param;

public class SlackbotTest {

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient()
				.register(LoggingFilter.class)
				;
		String response = client.target("https://telegenie.slack.com/services/hooks/slackbot")
//				.queryParam(Param.CLIENT_ID, "16665703089.16667560160")
				.queryParam(Param.TOKEN, "xoxb-16669781683-Jna8wLJVRfz66ciW44NJf7l9")
				.queryParam(Param.CHANNEL, "#general")
				.request().get(String.class);
		System.out.println(response);
	}
	
}
