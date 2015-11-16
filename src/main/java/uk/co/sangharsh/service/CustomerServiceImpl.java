package uk.co.sangharsh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.sangharsh.common.model.Customer;
import uk.co.sangharsh.dao.BaseDao;
import uk.co.sangharsh.dao.CustomerDao;

@Service
public class CustomerServiceImpl extends AsbtractBaseServiceImpl<Customer> implements CustomerService {
	
	@Autowired 
	private CustomerDao customerDAO;
	
	@Override
	protected BaseDao<Customer> getDao() {
		return this.customerDAO;
	}
}
