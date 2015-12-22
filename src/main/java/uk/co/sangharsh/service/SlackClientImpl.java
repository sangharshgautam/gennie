package uk.co.sangharsh.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.slack.api.client.param.Param;
import com.slack.api.client.pojo.Message;
import com.slack.api.client.pojo.response.ChannelHistoryResponse;
import com.slack.api.client.pojo.response.PostMessageResponse;
import com.slack.api.client.type.Method;
import com.slack.api.client.type.Slack;

@Service
public class SlackClientImpl implements SlackClient {

	@Value("${slack.client.id}")
	private String clientId;
	
	@Value("${slack.client.secret}")
	private String clientSecret;
	
	@Value("${slack.base.url}")
	private String slackBaseUrl;
	
	private static final Logger LOGGER = Logger.getLogger(SlackClientImpl.class.getName());
	
	private Client client; 
	
	@Autowired
	private NlpClient nlpClient;
	
	@PostConstruct
	public void setClientProperties(){
		ClientConfig cc = new ClientConfig()
				.property(UnmarshallerProperties.JSON_ATTRIBUTE_PREFIX, "@")
				.property(MarshallerProperties.JSON_ATTRIBUTE_PREFIX, "@")
				.register(MoxyJsonFeature.class)
				.register(new LoggingFilter(LOGGER, true))
				.register(MultiPartFeature.class);
		this.client = ClientBuilder.newClient(cc);
	}
	
	@Override
	public String authorize(String code) {
		String resp = client.target("https://slack.com/api/oauth.access")
				.queryParam(Param.CLIENT_ID, clientId)
				.queryParam(Param.CLIENT_SECRET, clientSecret)
				.queryParam(Param.CODE, code)
				.request().get(String.class);
		return resp;
	}
	private static final String CHANNEL = "C0GNNS5PE"; //#eclipse

	private static final String BOT_TOKEN = "xoxp-16665703089-16666379522-16669191650-a785839479";
	
	private ChannelHistoryResponse channelHistory() {
		Map<String, String> params = new HashMap<String, String>(){{
			put(Param.TOKEN, BOT_TOKEN);
			put(Param.CHANNEL, CHANNEL);
		}};
		return call(client, Slack.Channel.HISTORY, params, ChannelHistoryResponse.class);
	}
	
	@Override
	public void postMessage(final String command) {
		List<Message> messages = channelHistory().messages();
		StringBuilder builder =  new StringBuilder();
		for(Message message: messages){
			if(!message.isToIgnore()){
				builder.append(message.text()).append(". ");
			}
		}
		final String text = builder.toString();
		final List<String> docs = nlpClient.summarize(text);
		StringBuilder repBuilder = new StringBuilder();
		for(String doc: docs){
			repBuilder.append(doc).append("\n");
		}
		final String summary = repBuilder.toString();
		Map<String, String> params = new HashMap<String, String>(){{
			put(Param.TOKEN, BOT_TOKEN);
			put(Param.CHANNEL, CHANNEL);
			put(Param.TEXT, summary);
			put(Param.USERNAME, "Summarizer");
			put(Param.AS_USER, "false");
		}};
		PostMessageResponse response = call(client, Slack.Chat.POST_MESSAGE, params, PostMessageResponse.class);
	}
	private <T> T call(Client client, Method method, Map<String, String> params, Class<T> clazz) {
		WebTarget target = client.target(slackBaseUrl).path(method.mName());
		for(Entry<String, String> entry : params.entrySet()){
			target = target.queryParam(entry.getKey(), entry.getValue());
		}
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		return response.readEntity(clazz);
	}
}
