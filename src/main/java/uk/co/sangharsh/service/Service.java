package uk.co.sangharsh.service;

public interface Service<T> {
	public void create(T entity);
	
	public void merge(T entity);

	public T findBy(int i);

	public void update(T entity);
}
