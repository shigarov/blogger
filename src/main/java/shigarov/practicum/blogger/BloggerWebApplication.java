package shigarov.practicum.blogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.event.EventListener;

import shigarov.practicum.blogger.storage.StorageProperties;
import shigarov.practicum.blogger.storage.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class BloggerWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggerWebApplication.class, args);
	}

	@Autowired
	StorageService storageService;

	@EventListener(ApplicationReadyEvent.class)
	public void initStorage() {
		storageService.init();
	}

}
