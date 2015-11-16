package uk.co.sangharsh.dao;

import java.util.List;

import org.telegram.client.pojo.Update;

public interface UpdateDao extends Dao<Update>{

	List<Update> findUnprocessed();
	
}
