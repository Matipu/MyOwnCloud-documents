package cloud.adapter.mongo.config;

import cloud.adapter.mongo.FileMongoAdapter;
import cloud.application.ports.out.SaveFile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllersConfig {

    @Bean
    SaveFile fileMongoAdapter() {
        return new FileMongoAdapter();
    }
}
