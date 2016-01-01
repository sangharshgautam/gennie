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

import ai.wit.api.client.header.Header;
import ai.wit.api.client.param.Param;
import ai.wit.api.client.pojo.Message;

@Service
public class WitClientImpl implements WitClient{
	
	@Value("${wit.ai.api.auth.token}")
	private String token;
	
	@Value("${wit.base.url}")
	private String baseUrl;
	
	@Value("${wit.ai.api.version}")
	private String apiVersion;
	
	private static final Logger LOGGER = Logger.getLogger(WitClientImpl.class.getName());
	
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
	
	public Message query(String query) {
		Message message = client.target(baseUrl).path("message")
				.queryParam(Param.VERSION, apiVersion)
				.queryParam(Param.QUERY, query)
				.request().header(Header.AUTH_KEY, token)
				.get(Message.class);
		return message;
	}
}
