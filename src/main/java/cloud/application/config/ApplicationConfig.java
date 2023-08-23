package cloud.application.config;

import cloud.application.ports.out.SaveFile;
import cloud.application.service.FileService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    FileService fileService(SaveFile saveFile) {
        return new FileService(saveFile);
    }
}
