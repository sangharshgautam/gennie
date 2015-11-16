package uk.co.sangharsh.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractBaseDaoImpl<T> implements BaseDao<T>{
	@Autowired
	private SessionFactory sessionFactory;
	private Class<T> clazz;

	protected AbstractBaseDaoImpl(Class<T> clazz){
		this.clazz = clazz;
	}
	
	@Override
	public void create(T customer) {
		getSessionFactory().save(customer);
	}

	@Override
	public T findBy(int id) {
		return (T)getSessionFactory().get(clazz, id);
	}

	

	@Override
	public void update(T customer) {
		getSessionFactory().update(customer);
	}
	
	protected Session getSessionFactory() {
		return sessionFactory.getCurrentSession();
	}
}
