package uk.co.sangharsh.common.model;

public class CustomerTestUtil {
	
	private CustomerTestUtil(){}
	
	public static Customer dummy() {
		Customer customer = customer();
		return customer;
	}
	
	public static Customer usingName(String name) {
		Customer customer = customer();
		customer.setName(name);
		return customer;
	}

	private static Customer customer() {
		Customer customer = new Customer();
        customer.setName("Pankaj");
        Address address = new Address();
        address.setCountry("India");
        // setting value more than 20 chars, so that SQLException occurs
        address.setCity("Albany Dr, San Jose, CA 95129");
        customer.setAddress(address);
		return customer;
	}
}
