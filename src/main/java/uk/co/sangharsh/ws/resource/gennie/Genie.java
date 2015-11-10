package uk.co.sangharsh.ws.resource.gennie;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import uk.co.sangharsh.ws.resource.TelegramResource;


@ApplicationPath("api")
//@Configuration
//@ComponentScan({"uk.co.sangharsh.telegram", "uk.co.sangharsh.bot.service"})
//@EnableTransactionManagement 
public class Genie extends ResourceConfig{
//	private static final Logger LOGGER = Logger.getLogger(Gennie.class.getName());
	public Genie() {
		super();
		packages(TelegramResource.class.getPackage().getName());
		register(MoxyJsonFeature.class);
		register(LoggingFilter.class);
		register(MultiPartFeature.class);
    }
}
