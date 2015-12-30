package uk.co.sangharsh.service;

import java.util.List;

import uk.co.sangharsh.nlp.resource.pojo.Conversation;

public interface NlpClient {
	public List<String> summarize(String text);

	public List<String> summarize(Conversation conversation);

	public List<String> actionItems(Conversation conversation);
}
