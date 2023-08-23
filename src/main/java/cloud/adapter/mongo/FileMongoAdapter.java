package cloud.adapter.mongo;

import cloud.adapter.mongo.mapper.FileEntityMapper;
import cloud.adapter.mongo.repository.FileRepository;
import cloud.application.model.File;
import cloud.application.ports.out.SaveFile;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

public record FileMongoAdapter(FileRepository fileRepository,
                               FileEntityMapper fileEntityMapper,
                               GridFsTemplate gridFsTemplate) implements SaveFile {

    @Override
    public void saveFile(File file) {
        String gridFsId = gridFsTemplate.store(file.getContent(), file.getName(), file.getContentType()).toString();
        fileRepository.save(fileEntityMapper.mapFileToFileEntity(file, gridFsId));
    }
}
