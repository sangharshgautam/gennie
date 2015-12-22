package uk.co.sangharsh.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.filter.LoggingFilter;
import org.springframework.stereotype.Service;

import com.rhcloud.finnler.nlp.Result;

@Service
public class NlpClientImpl implements NlpClient {

	private static final String nlpBaseUrl = "http://nlp-telegenie.rhcloud.com/api";
	private Client client = ClientBuilder.newClient().register(LoggingFilter.class);
	
	@Override
	public String summarize(String text) {
		
		Result result = client.target(nlpBaseUrl).path("nlp/summarize/1").request().get(Result.class);
		return result.getDoc().get(0);
	}

}
