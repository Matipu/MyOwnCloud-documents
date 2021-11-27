package cloud.adapter.controllers.config;

import cloud.adapter.controllers.mapper.AddFileRequestMapper;
import cloud.adapter.controllers.mapper.AddFileRequestMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllersConfig {

    @Bean
    AddFileRequestMapper addFileRequestMapper() {
        return new AddFileRequestMapperImpl();
    }
}
