package uk.co.sangharsh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.client.pojo.Chat;

import uk.co.sangharsh.dao.ChatDao;
import uk.co.sangharsh.dao.Dao;

@Service
public class ChatServiceImpl extends AsbtractServiceImpl<Chat> implements ChatService {
	
	@Autowired 
	private ChatDao chatDao;
	
	@Override
	protected Dao<Chat> getDao() {
		return this.chatDao;
	}
}
