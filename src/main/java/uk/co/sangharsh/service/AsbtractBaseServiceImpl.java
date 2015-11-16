package uk.co.sangharsh.service;

import org.springframework.transaction.annotation.Transactional;

import uk.co.sangharsh.dao.BaseDao;

@Transactional
public abstract class AsbtractBaseServiceImpl<T> implements BaseService<T> {
	
	
	@Override
    public void create(T customer) {
		getDao().create(customer);
    }

	@Override
    public T findBy(int id) {
		return getDao().findBy(id);
	}

	@Override
	public void update(T customer) {
		getDao().update(customer);
	}
	protected abstract BaseDao<T> getDao();
}
