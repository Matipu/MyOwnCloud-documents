package cloud.adapter.api.config;

import cloud.adapter.api.api.FilesController;
import cloud.adapter.api.mapper.AddFileRequestMapper;
import cloud.adapter.api.mapper.AddFileRequestMapperImpl;
import cloud.adapter.api.mapper.FileResponseMapper;
import cloud.adapter.api.mapper.FileResponseMapperImpl;
import cloud.application.ports.in.AddFileUseCase;
import cloud.application.ports.in.DeleteFileUseCase;
import cloud.application.ports.in.GetFileUseCase;
import cloud.application.ports.in.UpdateFileUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllersConfig {

    @Bean
    AddFileRequestMapper addFileRequestMapper() {
        return new AddFileRequestMapperImpl();
    }

    @Bean
    FileResponseMapper fileResponseMapper() {
        return new FileResponseMapperImpl();
    }

    @Bean
    FilesController filesController(AddFileRequestMapper addFileRequestMapper,
                                    FileResponseMapper fileResponseMapper,
                                    AddFileUseCase addFileUseCase,
                                    GetFileUseCase getFileUseCase,
                                    DeleteFileUseCase deleteFileUseCase,
                                    UpdateFileUseCase updateFileUseCase) {
        return new FilesController(addFileRequestMapper, fileResponseMapper, addFileUseCase, getFileUseCase, deleteFileUseCase, updateFileUseCase);
    }
}
