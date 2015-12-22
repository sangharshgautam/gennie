package uk.co.sangharsh.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.filter.LoggingFilter;
import org.springframework.stereotype.Service;

import com.rhcloud.finnler.nlp.Result;
import com.rhcloud.finnler.nlp.SummarizeRequest;

@Service
public class NlpClientImpl implements NlpClient {

	private static final String nlpBaseUrl = "http://nlp-telegenie.rhcloud.com/api";
	private Client client = ClientBuilder.newClient().register(LoggingFilter.class);
	
	@Override
	public String summarize(String text) {
		SummarizeRequest request = SummarizeRequest.using(3, text);
		Result result = client.target(nlpBaseUrl).path("nlp/summarize").request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON), Result.class);
		return result.getDoc().get(0);
	}

}
