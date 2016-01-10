package uk.co.sangharsh.service;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.filter.LoggingFilter;
import org.springframework.stereotype.Service;

import uk.co.sangharsh.nlp.resource.pojo.Conversation;
import uk.co.sangharsh.nlp.resource.pojo.Result;
import uk.co.sangharsh.nlp.resource.pojo.SummarizeRequest;

@Service
public class NlpClientImpl implements NlpClient {

	private static final String nlpBaseUrl = "http://52.48.32.80:8080/nlp/api";
	private Client client = ClientBuilder.newClient().register(LoggingFilter.class);
	
	@Override
	public List<String> summarize(String text) {
		SummarizeRequest request = SummarizeRequest.using(3, text);
		Result result = client.target(nlpBaseUrl).path("nlp/summarize").request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON), Result.class);
		return result.getDoc();
	}

	@Override
	public List<String> summarize(Conversation conversation) {
		Result result = client.target(nlpBaseUrl).path("nlp/summarize/conversation").path("3").request(MediaType.APPLICATION_JSON).post(Entity.entity(conversation, MediaType.APPLICATION_JSON), Result.class);
		return result.getDoc();
	}

	@Override
	public List<String> actionItems(Conversation conversation) {
		Result result = client.target(nlpBaseUrl).path("nlp/actionitems/conversation").request(MediaType.APPLICATION_JSON).post(Entity.entity(conversation, MediaType.APPLICATION_JSON), Result.class);
		return result.getDoc();
	}

}
