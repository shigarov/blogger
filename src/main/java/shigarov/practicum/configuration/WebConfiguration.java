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
    //@Value("${upload.path}") // Относительный путь
    //private String uploadPath;

    private final String uploadPath;
    private final String uploadDir;

    @Autowired
    private ServletContext servletContext;

    public WebConfiguration(
            @Value("${upload.path}") String uploadPath,
            @Value("${upload.dir}") String uploadDir
    ) {
        this.uploadPath = uploadPath;
        this.uploadDir = uploadDir;
    }

    // Дать доступ к папке upload с картинками
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/images/**")
//                .addResourceLocations("classpath:/images/");
        String pathPatterns = File.separator + uploadDir + File.separator + "**";
        //String pathPatterns = "/upload/**";
        String locations = "file:" + uploadPath + File.separator + uploadDir + File.separator;

        // Получаем абсолютный путь к папке upload
        String realPath = servletContext.getRealPath(uploadPath + File.separator + uploadDir);
        try {
            Files.createDirectories(Paths.get("webapps/blogger/upload"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:webapps/blogger/upload/");
                //.addResourceLocations("file:" + realPath + "/");

        //        registry.addResourceHandler("/upload/**")
//                .addResourceLocations("file:" + uploadPath);//("file:/C:/Tomcat/Tomcat-11.0/upload/");
        registry.addResourceHandler(pathPatterns)
                .addResourceLocations(locations);

    }
    // Для поддержки GET-запросов с параметром типа Pageable
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add( new PageableHandlerMethodArgumentResolver());
    }

    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}



