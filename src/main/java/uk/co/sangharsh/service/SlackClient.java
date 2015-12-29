package uk.co.sangharsh.service;

import com.slack.api.client.form.CommandForm;
import com.slack.api.client.pojo.response.PostMessageResponse;

public interface SlackClient {
	public String authorize(String code);

	public void postMessage(String text);

	public PostMessageResponse respondTo(CommandForm commandForm);
}
