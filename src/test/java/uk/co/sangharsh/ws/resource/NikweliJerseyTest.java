package uk.co.sangharsh.ws.resource;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.moxy.xml.MoxyXmlFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.SpringLifecycleListener;
import org.glassfish.jersey.test.JerseyTestNg;
import org.glassfish.jersey.test.TestProperties;
import org.springframework.web.filter.RequestContextFilter;

import uk.co.sangharsh.common.util.Constants;

public class NikweliJerseyTest extends JerseyTestNg.ContainerPerClassTest {

	

	@Override
	protected final Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		ResourceConfig rc = new ResourceConfig()
				.packages(CustomerResource.class.getPackage().getName());
		rc.register(MoxyXmlFeature.class);
		rc.register(SpringLifecycleListener.class);
		rc.register(RequestContextFilter.class);

		rc.property("contextConfigLocation", Constants.CLASSPATH_SPRING_XML);
		return rc;
	}

	/*private MoxyXmlFeature moxy() {
		// Configure Properties.
		final Map<String, Object> properties = new HashMap<String, Object>();
		// ...
		 
		// Obtain a ClassLoader you want to use.
		final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		return new MoxyXmlFeature(
		        properties,
		        classLoader,
		        true, // Flag to determine whether eclipselink-oxm.xml file should be used for lookup.
		        Customer.class, Address.class  // Classes to be bound.
		    );
	}*/
}
