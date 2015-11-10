package uk.co.sangharsh.dao;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import uk.co.sangharsh.common.model.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void create(Customer customer) {
		getSessionFactory().save(customer);
	}

	@Override
	public Customer findBy(String uuid) {
		return (Customer)getSessionFactory().get(Customer.class, uuid);
	}

	

	@Override
	public void update(Customer customer) {
		getSessionFactory().update(customer);
	}
	
	private Session getSessionFactory() {
		return sessionFactory.getCurrentSession();
	}
}
