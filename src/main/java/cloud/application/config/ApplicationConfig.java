package cloud.application.config;

import cloud.application.ports.out.*;
import cloud.application.service.ContentFileService;
import cloud.application.service.FileService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    FileService fileService(SaveFile saveFile,
                            GetPersistedFile getPersistedFile,
                            RemovePersistedFile removePersistedFile,
                            UpdatePersistedFile updatePersistedFile,
                            FileCompressor fileCompressor) {
        return new FileService(saveFile, getPersistedFile, removePersistedFile, updatePersistedFile, fileCompressor);
    }

    @Bean
    ContentFileService contentFileService(GetPersistedFile getPersistedFile) {
        return new ContentFileService(getPersistedFile);
    }
}
