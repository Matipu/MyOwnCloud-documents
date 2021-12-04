package cloud.adapter.mongo.config;

import cloud.adapter.mongo.FileMongoAdapter;
import cloud.adapter.mongo.mapper.FileEntityMapper;
import cloud.adapter.mongo.mapper.FileEntityMapperImpl;
import cloud.adapter.mongo.repository.FileRepository;
import cloud.application.ports.out.SaveFile;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import java.util.Collection;
import java.util.Collections;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String connectionString;

    @Bean
    SaveFile fileMongoAdapter(FileRepository fileRepository, FileEntityMapper fileEntityMapper, GridFsTemplate gridFsTemplate) {
        return new FileMongoAdapter(fileRepository, fileEntityMapper, gridFsTemplate);
    }

    @Bean
    FileEntityMapper fileEntityMapper() {
        return new FileEntityMapperImpl();
    }

    @Bean
    public GridFsTemplate gridFsTemplate(MappingMongoConverter mongoConverter) {
        return new GridFsTemplate(mongoDbFactory(), mongoConverter);
    }

    @Override
    protected String getDatabaseName() {
        return "cloud";
    }

    @Override
    public MongoClient mongoClient() {
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Override
    public Collection getMappingBasePackages() {
        return Collections.singleton("cloud.adapter.mongo.repository");
    }
}