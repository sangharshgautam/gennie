package uk.co.sangharsh.ws.resource;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;

import java.util.List;

import javax.ws.rs.core.GenericType;

import org.telegram.client.pojo.Update;
import org.telegram.client.pojo.User;
import org.testng.annotations.Test;


public class TelegramResourceTest extends NikweliJerseyTest{
 
    private static final String UUID = "2c91880c508f9d1a01508f9d35f60000";


	@Test(priority = 1)
    public void shouldGetMe() throws Exception {
			final User user = target().path(TelegramResource.ROOT).path(TelegramResource.GET_ME)
				.request()
				.accept(APPLICATION_JSON_TYPE).get(User.class);
    }
	@Test(priority = 1)
    public void shouldGetUpdates() throws Exception {
			final List<Update> updates = target().path(TelegramResource.ROOT).path(TelegramResource.GET_UPDATES)
				.request()
				.accept(APPLICATION_JSON_TYPE).get(new GenericType<List<Update>>() {});
    }
    
}
