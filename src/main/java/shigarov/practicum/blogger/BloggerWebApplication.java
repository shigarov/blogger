package shigarov.practicum.blogger;

import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;

import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import shigarov.practicum.blogger.populator.PostPopulator;
import shigarov.practicum.blogger.storage.StorageProperties;
import shigarov.practicum.blogger.storage.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class BloggerWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggerWebApplication.class, args);
	}

	@Autowired
	private Environment env;

	@Autowired
	private ApplicationContext context;

	@Autowired
	private StorageService storageService;

	@EventListener(ApplicationReadyEvent.class)
	public void initStorage() {
		storageService.init();
		if(env.acceptsProfiles(Profiles.of("dev"))) {
			storageService.deleteAll();
			PostPopulator postPopulator = context.getBeanProvider(PostPopulator.class).getObject();
			postPopulator.populate(115);
		}
		storageService.init();
	}

	@PreDestroy
	public void destroy() {
		System.out.println("Callback triggered - @PreDestroy.");
	}

}
