package uk.co.sangharsh.service;

import com.slack.api.client.form.CommandForm;

public interface SlackClient {
	public String authorize(String code);

	public void postMessage(String text);

	public void respondTo(CommandForm commandForm);
}
