package uk.co.sangharsh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.client.pojo.User;

import uk.co.sangharsh.dao.BaseDao;
import uk.co.sangharsh.dao.UserDao;

@Service
public class UserServiceImpl extends AsbtractBaseServiceImpl<User> implements UserService {
	
	@Autowired 
	private UserDao userDao;
	
	@Override
	protected BaseDao<User> getDao() {
		return this.userDao;
	}
}
