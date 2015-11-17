package uk.co.sangharsh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.client.pojo.Message;

import uk.co.sangharsh.dao.Dao;
import uk.co.sangharsh.dao.MessageDao;

@Service
public class MessageServiceImpl extends AsbtractServiceImpl<Message> implements MessageService{
	
	@Autowired
	private MessageDao messsageDao;
	
	@Override
	protected Dao<Message> getDao() {
		return this.messsageDao;
	}
}
