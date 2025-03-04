package shigarov.practicum.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"shigarov.practicum"})
@PropertySource("classpath:test-application.properties") // Для считывания test-application.properties
@EnableSpringDataWebSupport
public class TestWebConfiguration implements WebMvcConfigurer {
}



