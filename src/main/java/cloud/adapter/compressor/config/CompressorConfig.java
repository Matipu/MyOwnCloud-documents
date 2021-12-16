package cloud.adapter.compressor.config;

import cloud.adapter.compressor.FileCompressorAdapter;
import cloud.adapter.compressor.mapper.FileCompressorMapper;
import cloud.adapter.compressor.mapper.FileCompressorMapperImpl;
import cloud.application.ports.in.GetContentFileUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CompressorConfig {

    @Bean
    FileCompressorMapper fileCompressorMapper() {
        return new FileCompressorMapperImpl();
    }

    @Bean
    FileCompressorAdapter fileCompressorAdapter(FileCompressorMapper fileCompressorMapper, GetContentFileUseCase getContentFileUseCase) {
        return new FileCompressorAdapter(fileCompressorMapper, getContentFileUseCase);
    }

}