package com.slack.api.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

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

	private static final String SLACK_API_ROOT = "https://slack.com/api";

	private static final String BOT_TOKEN = "xoxb-16669781683-Jna8wLJVRfz66ciW44NJf7l9";
	
	private static final String TEST_API_TOKEN = "xoxp-16665703089-16666379522-16669191650-a785839479";
	
	private static final String TEST_BOT_TOKEN = "xoxb-16772128277-A4iKHJziPQSFmxVmUOXP85jc";
	
	private Client client = ClientBuilder.newClient().register(LoggingFilter.class);
	
	
	@Test
	public void shouldPing(){
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
	public void testAuth(){
		String response = call(client, Slack.Api.TEST, new HashMap<String, String>(){{
			put(Param.TOKEN, TEST_API_TOKEN);
			put(Param.SCOPE, "identity");
		}}, String.class);
		System.out.println(response);
//		Assert.assertTrue(response.token());
	}
	@Test
	public void testChannelList() {
		ChannelListResponse response = call(client, Slack.Channel.LIST, new HashMap<String, String>(){{
			put(Param.TOKEN, TEST_BOT_TOKEN);
		}}, ChannelListResponse.class);
		System.out.println(response);
		boolean ok = response.isOk();
		System.out.println(response.error());
		Assert.assertTrue(ok);
	}
	
	@Test
	public void testChannelHistory() {
		ChannelHistoryResponse response = call(client, Slack.Channel.HISTORY, new HashMap<String, String>(){{
			put(Param.TOKEN, BOT_TOKEN);
			put(Param.CHANNEL, "#general");
		}}, ChannelHistoryResponse.class);
		System.out.println(response);
		Assert.assertTrue(response.isOk());
	}
	
	@Test
	public void testPostMessage() {
		PostMessageResponse response = call(client, Slack.Chat.POST_MESSAGE, new HashMap<String, String>(){{
			put(Param.TOKEN, BOT_TOKEN);
			put(Param.CHANNEL, "@sangharshim");
			put(Param.TEXT, "Hello dude");
		}}, PostMessageResponse.class);
		System.out.println(response);
		Assert.assertTrue(response.isOk());
	}
	
	private static <T> T call(Client client, Method method, Map<String, String> params, Class<T> clazz) {
		WebTarget target = client.target(SLACK_API_ROOT).path(method.mName());
		for(Entry<String, String> entry : params.entrySet()){
			target.queryParam(entry.getKey(), entry.getValue());
		}
		T response = target.request(MediaType.APPLICATION_JSON).get(clazz);
		return response;
	}
}
