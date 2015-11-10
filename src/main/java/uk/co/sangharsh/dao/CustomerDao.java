package uk.co.sangharsh.dao;

import uk.co.sangharsh.common.model.Customer;

public interface CustomerDao {

	public void create(Customer customer);

	public Customer findBy(String uuid);

	public void update(Customer customer);
}
