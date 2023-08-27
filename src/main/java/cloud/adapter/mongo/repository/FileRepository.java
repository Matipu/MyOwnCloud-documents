package cloud.adapter.mongo.repository;

import cloud.adapter.mongo.model.FileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FileRepository extends MongoRepository<FileEntity, String> {

    List<FileEntity> findByPath(String path);

    List<FileEntity> findByPathRegex(String pathRegex);

}