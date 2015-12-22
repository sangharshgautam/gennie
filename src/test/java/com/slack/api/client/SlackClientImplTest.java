package com.slack.api.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.Assert;
import org.testng.annotations.Test;

import com.slack.api.client.param.Param;
import com.slack.api.client.pojo.response.ChannelHistoryResponse;
import com.slack.api.client.pojo.response.ChannelListResponse;
import com.slack.api.client.pojo.response.PingResponse;
import com.slack.api.client.pojo.response.PostMessageResponse;
import com.slack.api.client.type.Method;
import com.slack.api.client.type.Slack;

public class SlackClientImplTest {

	private static final String CHANNEL = "C0GNNS5PE"; //#eclipse

	private static final String SLACK_API_ROOT = "https://slack.com/api";

	private static final String BOT_TOKEN = "xoxp-16665703089-16666379522-16669191650-a785839479";
	
	
	private Client client = ClientBuilder.newClient().register(LoggingFilter.class);
	
	
	@Test
	public void apiTest(){
		PingResponse response = call(client, Slack.Api.TEST, new HashMap<String, String>(){{
			put(Param.TOKEN, BOT_TOKEN);
		}}, PingResponse.class);
		Assert.assertTrue(response.isOk());
	}
	
/*	@Test
	public void testGetToken(){
		WebTarget target = client.target("https://slack.com/oauth/authorize")
		.queryParam(Param.CLIENT_ID, "16665703089.16667560160")
		.queryParam(Param.SCOPE, StringUtils.join(new String[]{Scope.CHANNELS_READ}, " "));
		String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
		System.out.println(response);
	}*/
	
	@Test
	public void authTest(){
		String response = call(client, Slack.Api.TEST, new HashMap<String, String>(){{
			put(Param.TOKEN, BOT_TOKEN);
			put(Param.SCOPE, "identity");
		}}, String.class);
		System.out.println(response);
//		Assert.assertTrue(response.token());
	}
	@Test
	public void channelList() {
		ChannelListResponse response = call(client, Slack.Channel.LIST, new HashMap<String, String>(){{
			put(Param.TOKEN, BOT_TOKEN);
		}}, ChannelListResponse.class);
		System.out.println(response);
		boolean ok = response.isOk();
		System.out.println(response.error());
		Assert.assertTrue(ok);
	}
	
	@Test
	public void channelHistory() {
		String response = call(client, Slack.Channel.HISTORY, new HashMap<String, String>(){{
			put(Param.TOKEN, BOT_TOKEN);
			put(Param.CHANNEL, CHANNEL);
		}}, String.class);
		System.out.println(response);
	}
	
	@Test
	public void postMessage() {
		String response = call(client, Slack.Chat.POST_MESSAGE, new HashMap<String, String>(){{
			put(Param.TOKEN, BOT_TOKEN);
			put(Param.CHANNEL, CHANNEL);
			put(Param.TEXT, "Hello I am a bot");
			put(Param.USERNAME, "Summarizer");
			put(Param.AS_USER, "true");
		}}, String.class);
		System.out.println(response);
	}
	
	private static <T> T call(Client client, Method method, Map<String, String> params, Class<T> clazz) {
		WebTarget target = client.target(SLACK_API_ROOT).path(method.mName());
		for(Entry<String, String> entry : params.entrySet()){
			target = target.queryParam(entry.getKey(), entry.getValue());
		}
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		Assert.assertTrue(response.getStatus() == 200);
		return response.readEntity(clazz);
	}
}
