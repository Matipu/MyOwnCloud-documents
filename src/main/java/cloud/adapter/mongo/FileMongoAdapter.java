package cloud.adapter.mongo;

import cloud.adapter.mongo.mapper.FileEntityMapper;
import cloud.adapter.mongo.repository.FileRepository;
import cloud.application.model.File;
import cloud.application.model.FileId;
import cloud.application.ports.out.GetPersistedFile;
import cloud.application.ports.out.SaveFile;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import java.util.List;

public record FileMongoAdapter(FileRepository fileRepository,
                               FileEntityMapper fileEntityMapper,
                               GridFsTemplate gridFsTemplate) implements SaveFile, GetPersistedFile {

    @Override
    public void saveFile(File file) {
        String gridFsId = gridFsTemplate.store(file.getContent(), file.getName(), file.getContentType()).toString();
        String gridFsIconId = gridFsTemplate.store(file.getContent(), file.getName(), file.getContentType()).toString();
        fileRepository.save(fileEntityMapper.mapFileToFileEntity(file, gridFsId, gridFsIconId));
    }

    @Override
    public File getFile(FileId fileId) {
        return fileEntityMapper.mapFileEntityToFile(fileRepository.findById(fileId.getValue()).orElseThrow());
    }

    @Override
    public List<File> getAllFiles() {
        return fileEntityMapper.mapFileEntityListToFileList(fileRepository.findAll());

    }
}
