package cloud.adapter.api.config;

import cloud.adapter.api.api.FilesController;
import cloud.adapter.api.mapper.AddFileRequestMapper;
import cloud.adapter.api.mapper.AddFileRequestMapperImpl;
import cloud.application.ports.in.AddFileUseCase;
import cloud.application.ports.in.GetFileUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllersConfig {

    @Bean
    AddFileRequestMapper addFileRequestMapper() {
        return new AddFileRequestMapperImpl();
    }

    @Bean
    FilesController filesController(AddFileRequestMapper addFileRequestMapper,
                                    AddFileUseCase addFileUseCase,
                                    GetFileUseCase getFileUseCase) {
        return new FilesController(addFileRequestMapper, addFileUseCase, getFileUseCase);
    }
}
