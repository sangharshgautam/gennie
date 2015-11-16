package uk.co.sangharsh.service;

import org.springframework.transaction.annotation.Transactional;

import uk.co.sangharsh.dao.Dao;

@Transactional
public abstract class AsbtractServiceImpl<T> implements Service<T> {
	
	
	@Override
    public void create(T entity) {
		getDao().create(entity);
    }
	
	@Override
    public void merge(T entity) {
		getDao().merge(entity);
    }

	@Override
    public T findBy(int id) {
		return getDao().findBy(id);
	}

	@Override
	public void update(T customer) {
		getDao().update(customer);
	}
	protected abstract Dao<T> getDao();
}
