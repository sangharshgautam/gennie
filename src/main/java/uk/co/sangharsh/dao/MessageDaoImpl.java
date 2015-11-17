package uk.co.sangharsh.dao;

import org.springframework.stereotype.Repository;
import org.telegram.client.pojo.Message;

@Repository
public class MessageDaoImpl extends  AbstractBaseDaoImpl<Message> implements MessageDao{

	protected MessageDaoImpl() {
		super(Message.class);
	}
}
