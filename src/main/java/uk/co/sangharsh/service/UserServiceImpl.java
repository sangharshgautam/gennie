package uk.co.sangharsh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.client.pojo.User;

import uk.co.sangharsh.dao.Dao;
import uk.co.sangharsh.dao.UserDao;

@Service
public class UserServiceImpl extends AsbtractServiceImpl<User> implements UserService {
	
	@Autowired 
	private UserDao userDao;
	
	@Override
	protected Dao<User> getDao() {
		return this.userDao;
	}
}
