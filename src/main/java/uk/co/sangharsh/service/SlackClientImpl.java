package uk.co.sangharsh.service;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.websocket.ClientEndpointConfig;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.tyrus.client.ClientManager;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import uk.co.sangharsh.nlp.resource.pojo.Conversation;
import uk.co.sangharsh.nlp.resource.pojo.Utterance;
import ai.wit.api.client.pojo.Normalized;

import com.slack.api.client.form.CommandForm;
import com.slack.api.client.form.CommandResponse;
import com.slack.api.client.param.Param;
import com.slack.api.client.pojo.Message;
import com.slack.api.client.pojo.response.ChannelHistoryResponse;
import com.slack.api.client.pojo.response.PostMessageResponse;
import com.slack.api.client.pojo.response.RtmStartResponse;
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
	
	private Session rtmSession;
	
	private static final String SENT_MESSAGE = "Hello World";
	
	@Autowired
	private WitClient witClient;
	
	@PostConstruct
	public void setClientProperties(){
		ClientConfig cc = new ClientConfig()
				.property(UnmarshallerProperties.JSON_ATTRIBUTE_PREFIX, "@")
				.property(MarshallerProperties.JSON_ATTRIBUTE_PREFIX, "@")
				.register(MoxyJsonFeature.class)
				.register(new LoggingFilter(LOGGER, true))
				.register(MultiPartFeature.class);
		this.client = ClientBuilder.newClient(cc);
//		this.connect();
	}
	private void connect(){
        final RtmStartResponse resp = this.client.target(slackBaseUrl).path("rtm.start").queryParam(Param.TOKEN, SlackClientImpl.BOT_TOKEN).request().get(RtmStartResponse.class);
        try {

            final ClientEndpointConfig cec = ClientEndpointConfig.Builder.create().build();

            ClientManager clientManager = ClientManager.createClient();
            clientManager.connectToServer(new Endpoint() {

                @Override
                public void onOpen(Session session, EndpointConfig config) {
                	SlackClientImpl.this.rtmSession = session;
                    try {
                    	SlackClientImpl.this.rtmSession.addMessageHandler(new MessageHandler.Whole<String>() {

                            @Override
                            public void onMessage(String message) {
                                System.out.println("Received message: "+message);
                            }
                        });
                    	SlackClientImpl.this.rtmSession.getBasicRemote().sendText(SENT_MESSAGE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }, cec, new URI(resp.wsUrl()));
        } catch (Exception e) {
            e.printStackTrace();
        }
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

	public static final String BOT_TOKEN = "xoxp-16665703089-16666379522-16669191650-a785839479";
	
	private ChannelHistoryResponse channelHistory(String latest, final long oldest) {
		Map<String, String> params = new HashMap<String, String>(){{
			put(Param.TOKEN, BOT_TOKEN);
			put(Param.CHANNEL, CHANNEL);
			put(Param.COUNT, "1000");
			put(Param.UNREADS, "1");
			put(Param.OLDEST, ""+oldest);
		}};
		if(StringUtils.isNotBlank(latest)){
			params.put(Param.LATEST, latest);
		}
		return call(client, Slack.Channel.HISTORY, params, ChannelHistoryResponse.class);
	}
	@Async
	@Override
	public void respondTo(CommandForm commandForm) {
		ai.wit.api.client.pojo.Message witResponse = witClient.query(commandForm.text());
		Normalized normalized = witResponse.firstOutcome().entities().firstDuration().normalized();
		List<Message> messages = new ArrayList<>();
		ChannelHistoryResponse channelHistory ;
		String latest = null;
		do {
			channelHistory = channelHistory(latest, normalized.getDateTime());
			List<Message> messagesChunk = channelHistory.messages();
			messages.addAll(messagesChunk);
			latest = messagesChunk.isEmpty() ? null : messagesChunk.get(messagesChunk.size()-1).ts();
		} while (channelHistory.hasMore());
			
		Conversation conversation = new Conversation();
		for(Message message: messages){
//			if(!message.isToIgnore()){
				conversation.add(Utterance.utterance(" <@"+message.user()+"> _", message.text()+"_"));
//			}
		}
		final List<String> docs = nlpClient.summarize(conversation);
		StringBuilder respBuilder = new StringBuilder();
		for(String doc: docs){
			respBuilder.append(doc).append("\n ");
		}
		final String summary = respBuilder.toString();
		CommandResponse entity = CommandResponse.using(summary);
		WebTarget target = client.target(commandForm.responseUrl());
		Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(entity, MediaType.APPLICATION_JSON));
	}
	@Override
	public void postMessage(final String message) {
		Map<String, String> params = new HashMap<String, String>(){{
			put(Param.TOKEN, BOT_TOKEN);
			put(Param.CHANNEL, CHANNEL);
			put(Param.TEXT, message);
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
