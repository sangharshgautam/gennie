package uk.co.sangharsh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.client.pojo.Update;
import org.telegram.client.pojo.User;

import uk.co.sangharsh.dao.BaseDao;
import uk.co.sangharsh.dao.UpdateDao;

@Service
public class UpdateServiceImpl extends AsbtractBaseServiceImpl<Update> implements UpdateService {
	
	@Autowired 
	private UpdateDao updateDao;
	
	@Autowired 
	private UserService userService;
	
	@Override
    public void create(Update update) {
		User from = update.getMessage().getFrom();
		User dbFrom = userService.findBy(from.getId());
		if(dbFrom == null){
			userService.create(from);
		}else{
			userService.merge(from);
		}
		getDao().create(update);
    }
	
	@Override
	protected BaseDao<Update> getDao() {
		return this.updateDao;
	}

	@Override
	public List<Update> findUnprocessed() {
		return updateDao.findUnprocessed();
	}
}
