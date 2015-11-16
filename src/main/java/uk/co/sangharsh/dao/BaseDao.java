package uk.co.sangharsh.dao;

public interface BaseDao<T> {

	public void create(T customer);

	public T findBy(int id);

	public void update(T customer);
}
