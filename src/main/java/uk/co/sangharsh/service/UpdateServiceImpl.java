package uk.co.sangharsh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.client.pojo.Telegram;
import org.telegram.client.pojo.Update;

import uk.co.sangharsh.dao.Dao;
import uk.co.sangharsh.dao.UpdateDao;

@Service
public class UpdateServiceImpl extends AsbtractServiceImpl<Update> implements UpdateService {
	
	@Autowired 
	private UpdateDao updateDao;
	
	@Autowired 
	private UserService userService;
	
	@Autowired 
	private ChatService chatService;
	
	@Override
    public void create(Update update) {
		Telegram message = update.getMessage();
		userService.merge(message.from());
		chatService.merge(message.chat());
		getDao().create(update);
    }
	
	@Override
	protected Dao<Update> getDao() {
		return this.updateDao;
	}

	@Override
	public List<Update> findUnprocessed() {
		return updateDao.findUnprocessed();
	}
}
