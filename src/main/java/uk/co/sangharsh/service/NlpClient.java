package uk.co.sangharsh.service;

import java.util.List;

public interface NlpClient {
	public List<String> summarize(String text);
}
