package uk.co.sangharsh.ws.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.co.sangharsh.common.model.Customer;
import uk.co.sangharsh.service.CustomerService;

@Component
@Path(CustomerResource.ROOT)
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

	public static final String ROOT = "customer";
	
	@Autowired
	private CustomerService customerService; 
	
	@POST
	public Customer create(Customer customer){
		customerService.create(customer);
		return customer;
	}
	
	@GET
	@Path("{uuid}")
	public Customer get(@PathParam("uuid") int id){
		return customerService.findBy(id);
	}
	
	@PUT
	public Customer update(Customer customer){
		customerService.update(customer);
		return customer;
	}
	
	
	
}
