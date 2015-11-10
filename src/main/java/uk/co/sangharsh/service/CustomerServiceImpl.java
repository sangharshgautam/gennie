package uk.co.sangharsh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.co.sangharsh.common.model.Customer;
import uk.co.sangharsh.dao.CustomerDao;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {
 
	@Autowired
    private CustomerDao customerDAO;
 
    @Override
    public void createCustomer(Customer customer) {
        customerDAO.create(customer);
    }

	@Override
    public Customer findBy(String uuid) {
		return customerDAO.findBy(uuid);
	}

	@Override
	public void update(Customer customer) {
		customerDAO.update(customer);
	}
 
}
