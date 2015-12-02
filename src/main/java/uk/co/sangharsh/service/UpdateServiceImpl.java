package uk.co.sangharsh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.client.pojo.Message;
import org.telegram.client.pojo.PhotoSize;
import org.telegram.client.pojo.Telegram;
import org.telegram.client.pojo.Update;
import org.telegram.client.pojo.User;

import uk.co.sangharsh.dao.Dao;
import uk.co.sangharsh.dao.TelegramDao;
import uk.co.sangharsh.dao.UpdateDao;

@Service
public class UpdateServiceImpl extends AsbtractServiceImpl<Update> implements UpdateService {
	
	@Autowired 
	private UpdateDao updateDao;
	
	@Autowired 
	private UserService userService;
	
	@Autowired 
	private ChatService chatService;
	
	@Autowired 
	private MessageService messageService;
	
	@Autowired
	private PhotoSizeService photoSizeService;
	
	@Autowired
	private TelegramDao telegramDao;
	
	@Override
    public void create(Update update) {
		Telegram message = update.getMessage();
		User forwardFrom = message.forwardFrom();
		if(forwardFrom != null){
			userService.merge(forwardFrom);
		}
		Message details = message.details();
		if(details != null){
			messageService.merge(details);
		}
		List<PhotoSize> photo = message.photo();
		if (photo != null) {
			photoSizeService.mergeAll(photo);
		}
		Telegram replyToMessage = message.replyToMessage();
		if(replyToMessage != null){
			telegramDao.merge(replyToMessage);
		}
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
