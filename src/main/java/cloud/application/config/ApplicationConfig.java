package cloud.application.config;

import cloud.application.ports.out.GetPersistedFile;
import cloud.application.ports.out.RemovePersistedFile;
import cloud.application.ports.out.SaveFile;
import cloud.application.ports.out.UpdatePersistedFile;
import cloud.application.service.FileService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    FileService fileService(SaveFile saveFile, GetPersistedFile getPersistedFile, RemovePersistedFile removePersistedFile, UpdatePersistedFile updatePersistedFile) {
        return new FileService(saveFile, getPersistedFile, removePersistedFile, updatePersistedFile);
    }
}
