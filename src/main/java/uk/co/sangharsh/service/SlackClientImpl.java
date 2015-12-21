package uk.co.sangharsh.service;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.slack.api.client.param.Param;

@Service
public class SlackClientImpl implements SlackClient {

	@Value("${slack.client.id}")
	private String clientId;
	
	@Value("${slack.client.secret}")
	private String clientSecret;
	
	private static final Logger LOGGER = Logger.getLogger(SlackClientImpl.class.getName());
	
	private Client client; 
	
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
//				.queryParam(Param.REDIRECT_URI, "http://gennie-finnler.rhcloud.com/api/slack/authorize/handle")
				.request().get(String.class);
		return resp;
	}

}
