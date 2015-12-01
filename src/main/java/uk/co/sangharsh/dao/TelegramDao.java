package uk.co.sangharsh.dao;

import java.util.List;

import org.telegram.client.pojo.Telegram;

public interface TelegramDao extends Dao<Telegram>{
	public List<Telegram> findByReplyTo(int id);
}
