package uk.co.sangharsh.service;

public interface SlackClient {
	public String authorize(String code);

	public void postMessage(String text);
}
