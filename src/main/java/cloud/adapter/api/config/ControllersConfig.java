package cloud.adapter.api.config;

import cloud.adapter.api.mapper.AddFileRequestMapper;
import cloud.adapter.api.mapper.AddFileRequestMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllersConfig {

    @Bean
    AddFileRequestMapper addFileRequestMapper() {
        return new AddFileRequestMapperImpl();
    }
}
