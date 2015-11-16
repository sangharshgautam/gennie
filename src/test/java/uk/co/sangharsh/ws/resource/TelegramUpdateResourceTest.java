package uk.co.sangharsh.ws.resource;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;

import java.util.List;

import javax.ws.rs.core.GenericType;

import org.telegram.client.pojo.Update;
import org.testng.annotations.Test;

public class TelegramUpdateResourceTest extends NikweliJerseyTest {
	@Test(priority = 1)
    public void shouldGetUpdates() throws Exception {
			final List<Update> updates = target().path(TelegramUpdateResource.ROOT).path(TelegramUpdateResource.FETCH_UPDATES)
				.request()
				.accept(APPLICATION_JSON_TYPE).get(new GenericType<List<Update>>() {});
    }
}
