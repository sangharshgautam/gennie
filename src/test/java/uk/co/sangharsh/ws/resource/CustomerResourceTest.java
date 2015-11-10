package uk.co.sangharsh.ws.resource;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_XML_TYPE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.testng.annotations.Test;

import uk.co.sangharsh.common.model.Customer;
import uk.co.sangharsh.common.model.CustomerTestUtil;


public class CustomerResourceTest extends NikweliJerseyTest{
 
    private static final String UUID = "2c91880c508f9d1a01508f9d35f60000";


	@Test(priority = 1)
    public void shouldGetJson() throws Exception {
    	Response response = get(APPLICATION_JSON_TYPE);
    }
    @Test(priority = 2)
    public void shouldGetXml() throws Exception {
    	Response response = get(APPLICATION_XML_TYPE);
    }
    @Test(priority = 3)
    public void shouldCreateCustomer(){
    	Customer customer = CustomerTestUtil.usingName("Sangharsh Gautam");
		final Customer response = target().path(CustomerResource.ROOT)
				.request()
				.accept(APPLICATION_JSON_TYPE).post(Entity.<Customer>entity(customer, APPLICATION_JSON_TYPE), Customer.class);
//		assertThat(response.getStatus(), equalTo(200));
    }
    
    @Test(priority = 4)
    public void shouldUpdateCustomer(){
    	Customer customer = CustomerTestUtil.usingName("Sangharsh Gautam");
		final Customer response = target().path(CustomerResource.ROOT)
				.request()
				.accept(APPLICATION_JSON_TYPE).post(Entity.<Customer>entity(customer, APPLICATION_JSON_TYPE), Customer.class);
//		assertThat(response.getStatus(), equalTo(200));
		System.out.println(response.getUuid());
		
		response.setName("Changed Name");
		final Customer responseUpdated = target().path(CustomerResource.ROOT)
				.request()
				.accept(APPLICATION_JSON_TYPE).put(Entity.<Customer>entity(response, APPLICATION_JSON_TYPE), Customer.class);
    }
    
    @Test(priority = 5)
    public void shouldValidateBean(){
    	
    }
    
    
	private Response get(MediaType accept) {
		final Response response = target().path(CustomerResource.ROOT).path(UUID).request().accept(accept).get(Response.class);
        assertThat(response.getStatus(), equalTo(200));
        return response;
	}
}
