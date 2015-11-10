package uk.co.sangharsh.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractJpaDao<T> {
	@PersistenceContext(unitName = "webPersistenceUnit")
	
	protected EntityManager entityManager;
	private Class<T> clazz;

	public AbstractJpaDao(Class<T> clazz) {
		super();
		this.clazz = clazz;
	}

	public void create(T entity) {
		entityManager.persist(entity);
	}

	public T findBy(String uuid) {
		return entityManager.find(clazz, uuid);
	}

	

	public void update(T entity) {
		entityManager.merge(entity);
	}
}
