package uk.co.sangharsh.dao;

import org.springframework.stereotype.Repository;
import org.telegram.client.pojo.Chat;

@Repository
public class ChatDaoImpl extends  AbstractBaseDaoImpl<Chat> implements ChatDao{

	protected ChatDaoImpl() {
		super(Chat.class);
	}

}
