package cloud.application.config;

import cloud.application.service.FileService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    FileService fileService() {
        return new FileService();
    }
}
