package uk.co.sangharsh.dao;

import org.springframework.stereotype.Repository;

import uk.co.sangharsh.common.model.Customer;

@Repository
public class CustomerDaoImpl extends  AbstractBaseDaoImpl<Customer> implements CustomerDao{

	protected CustomerDaoImpl() {
		super(Customer.class);
	}
}
