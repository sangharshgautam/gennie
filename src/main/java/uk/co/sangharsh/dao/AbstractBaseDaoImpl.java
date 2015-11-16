package uk.co.sangharsh.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractBaseDaoImpl<T> implements Dao<T>{
	@Autowired
	private SessionFactory sessionFactory;
	private Class<T> clazz;

	protected AbstractBaseDaoImpl(Class<T> clazz){
		this.clazz = clazz;
	}
	
	@Override
	public void create(T entity) {
		getSessionFactory().save(entity);
	}
	
	@Override
	public void merge(T entity) {
		getSessionFactory().merge(entity);
	}

	@Override
	public T findBy(int id) {
		return (T)getSessionFactory().get(clazz, id);
	}

	

	@Override
	public void update(T entity) {
		getSessionFactory().update(entity);
	}
	
	protected Session getSessionFactory() {
		return sessionFactory.getCurrentSession();
	}
}
