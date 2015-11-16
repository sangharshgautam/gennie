package uk.co.sangharsh.dao;

import java.util.List;

import org.telegram.client.pojo.Update;

public interface UpdateDao extends BaseDao<Update>{

	List<Update> findUnprocessed();
	
}
