package uk.co.sangharsh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.client.pojo.Update;

import uk.co.sangharsh.dao.BaseDao;
import uk.co.sangharsh.dao.UpdateDao;

@Service
public class UpdateServiceImpl extends AsbtractBaseServiceImpl<Update> implements UpdateService {
	
	@Autowired 
	private UpdateDao updateDao;
	
	@Override
	protected BaseDao<Update> getDao() {
		return this.updateDao;
	}

	@Override
	public List<Update> findUnprocessed() {
		return updateDao.findUnprocessed();
	}
}
