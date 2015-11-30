package uk.co.sangharsh.dao;

import org.springframework.stereotype.Repository;
import org.telegram.client.pojo.Telegram;

@Repository
public class TelegramDaoImpl extends  AbstractBaseDaoImpl<Telegram> implements TelegramDao {

	protected TelegramDaoImpl() {
		super(Telegram.class);
	}

}
