package uk.co.sangharsh.dao;

public interface BaseDao<T> {

	public void create(T entity);
	
	public void merge(T entity); 

	public T findBy(int id);

	public void update(T customer);
}
