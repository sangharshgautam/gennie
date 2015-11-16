package uk.co.sangharsh.service;

import java.util.List;

import org.telegram.client.pojo.Update;

public interface UpdateService extends Service<Update>{

	public void create(Update update);
	List<Update> findUnprocessed();
}
