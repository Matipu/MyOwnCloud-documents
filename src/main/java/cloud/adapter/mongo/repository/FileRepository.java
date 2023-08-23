package cloud.adapter.mongo.repository;

import cloud.adapter.mongo.model.FileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileRepository extends MongoRepository<FileEntity, String> {

}