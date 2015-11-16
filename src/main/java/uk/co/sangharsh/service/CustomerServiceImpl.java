package uk.co.sangharsh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.sangharsh.common.model.Customer;
import uk.co.sangharsh.dao.Dao;
import uk.co.sangharsh.dao.CustomerDao;

@Service
public class CustomerServiceImpl extends AsbtractServiceImpl<Customer> implements CustomerService {
	
	@Autowired 
	private CustomerDao customerDAO;
	
	@Override
	protected Dao<Customer> getDao() {
		return this.customerDAO;
	}
}
