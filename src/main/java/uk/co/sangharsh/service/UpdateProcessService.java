package uk.co.sangharsh.service;

import java.io.IOException;
import java.util.List;

import org.telegram.client.pojo.Update;

public interface UpdateProcessService {
	public void pullUpdates();
	public void pushUpdates(List<Update> updates);
	public void push(Update update);
	public void process() throws IOException, Exception;
}
