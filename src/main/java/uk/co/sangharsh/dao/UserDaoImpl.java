package uk.co.sangharsh.dao;

import org.springframework.stereotype.Repository;
import org.telegram.client.pojo.User;

@Repository
public class UserDaoImpl extends  AbstractBaseDaoImpl<User> implements UserDao{

	protected UserDaoImpl() {
		super(User.class);
	}

}
