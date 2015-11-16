package uk.co.sangharsh.service;

import java.util.List;

import org.telegram.client.pojo.Update;

public interface UpdateService extends BaseService<Update>{

	List<Update> findUnprocessed();
}
