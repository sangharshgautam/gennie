package uk.co.sangharsh.ws.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import uk.co.sangharsh.common.model.Customer;
import uk.co.sangharsh.common.model.CustomerTestUtil;
import uk.co.sangharsh.service.CustomerService;
import uk.co.sangharsh.service.CustomerServiceImpl;
 
public class TransactionManagerMain {
 
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:**/spring-*.xml");
 
        CustomerService customerManager = ctx.getBean("customerService",
                CustomerServiceImpl.class);
 
        Customer cust = CustomerTestUtil.dummy();
        customerManager.create(cust);
 
        ctx.close();
    }

}
