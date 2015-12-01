package uk.co.sangharsh.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.telegram.client.pojo.Telegram;

@Repository
public class TelegramDaoImpl extends  AbstractBaseDaoImpl<Telegram> implements TelegramDao {

	protected TelegramDaoImpl() {
		super(Telegram.class);
	}
	@Override
	public List<Telegram> findByReplyTo(int replyToId) {
		return (List<Telegram>)getSessionFactory().createCriteria(Telegram.class).add(Restrictions.eq("replyToMessage.id", replyToId)).list();
	}
}
