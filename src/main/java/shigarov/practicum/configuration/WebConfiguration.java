package shigarov.practicum.configuration;

import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"shigarov.practicum"})
@PropertySource("classpath:application.properties") // Для считывания application.properties
@EnableSpringDataWebSupport // Для поддержки GET-запросов с параметром типа Pageable
public class WebConfiguration implements WebMvcConfigurer {
    @Value("${upload.path}")
    private String uploadPath;

    @Value("${upload.dir}")
    private String uploadDir;

    // Дать доступ к папке upload с картинками
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        try {
            Files.createDirectories(Paths.get(uploadPath, uploadDir));
            registry.addResourceHandler("/" + uploadDir + "/**")
                    .addResourceLocations("file:" + uploadPath + "/" + uploadDir + "/");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Для поддержки GET-запросов с параметром типа Pageable
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
    }

    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}



