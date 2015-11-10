package uk.co.sangharsh.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractHibernateDao<T> {
	
	@Autowired
	private SessionFactory sessionFactory;

	private Class<T> clazz;

	public AbstractHibernateDao(Class<T> clazz) {
		super();
		this.clazz = clazz;
	}
	
	public void create(T entity) {
		getSessionFactory().save(entity);
	}

	public T findBy(String uuid) {
		return (T)getSessionFactory().get(clazz, uuid);
	}

	

	public void update(T entity) {
		getSessionFactory().update(entity);
	}
	
	private Session getSessionFactory() {
		return sessionFactory.getCurrentSession();
	}
}
