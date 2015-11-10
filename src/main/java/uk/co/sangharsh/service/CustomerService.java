package uk.co.sangharsh.service;

import uk.co.sangharsh.common.model.Customer;

public interface CustomerService {
	public void createCustomer(Customer customer);

	public Customer findBy(String id);

	public void update(Customer customer);
}
