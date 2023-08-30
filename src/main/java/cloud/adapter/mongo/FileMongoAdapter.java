package cloud.adapter.mongo;

import cloud.adapter.mongo.mapper.FileEntityMapper;
import cloud.adapter.mongo.model.FileEntity;
import cloud.adapter.mongo.repository.FileRepository;
import cloud.application.model.File;
import cloud.application.model.FileId;
import cloud.application.ports.out.GetPersistedFile;
import cloud.application.ports.out.RemovePersistedFile;
import cloud.application.ports.out.SaveFile;
import cloud.application.ports.out.UpdatePersistedFile;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public record FileMongoAdapter(FileRepository fileRepository,
                               FileEntityMapper fileEntityMapper,
                               GridFsTemplate gridFsTemplate) implements SaveFile, GetPersistedFile, RemovePersistedFile,
        UpdatePersistedFile {

    @Override
    public void saveFile(File file) {
        String gridFsId = null;
        if (file.getContent() != null) {
            gridFsId = gridFsTemplate.store(file.getContent(), file.getName(), file.getContentType()).toString();
        }

        String gridFsIconId = null;
        if (file.getIconContent() != null) {
            gridFsIconId = gridFsTemplate.store(file.getIconContent(), file.getName(), file.getContentType()).toString();
        }
        fileRepository.save(fileEntityMapper.mapFileToFileEntity(file, gridFsId, gridFsIconId));
    }

    @Override
    public File getFile(FileId fileId) {
        return fileEntityMapper.mapFileEntityToFile(fileRepository.findById(fileId.getValue()).orElseThrow());
    }

    @Override
    public InputStream getFileContent(FileId fileId) {
        FileEntity file = fileRepository.findById(fileId.getValue()).orElseThrow();
        return getContent(file.getGridFsId());
    }

    @Override
    public InputStream getFileIcon(FileId fileId) {
        FileEntity file = fileRepository.findById(fileId.getValue()).orElseThrow();
        return getContent(file.getGridFsIconId());
    }

    @Override
    public List<File> getFilesByPath(String path) {
        List<FileEntity> fileEntities = fileRepository.findByPath(path);
        return fileEntities.stream().map(fileEntityMapper::mapFileEntityToFile).collect(Collectors.toList());
    }

    @Override
    public List<FileId> getFileIdsByPathRegex(String pathRegex) {
        List<FileEntity> fileEntities = fileRepository.findByPathRegex(pathRegex);

        return fileEntities.stream().map(fileEntity -> FileId.of(fileEntity.getId())).collect(Collectors.toList());
    }

    @Override
    public List<File> getFilesByPathRegex(String pathRegex) {
        List<FileEntity> fileEntities = fileRepository.findByPathRegex(pathRegex);
        return fileEntities.stream().map(fileEntityMapper::mapFileEntityToFile).collect(Collectors.toList());
    }

    @Override
    public void updateFile(File file) {
        FileEntity fileEntity = fileRepository.findById(file.getFileId().getValue()).orElseThrow();
        fileEntity.setName(file.getName());
        fileEntity.setPath(file.getPath());
        fileRepository.save(fileEntity);
    }

    @Override
    public void removeFile(FileId fileId) {
        Optional<FileEntity> fileEntity = fileRepository.findById(fileId.getValue());
        FileEntity file = fileEntity.orElseThrow();
        gridFsTemplate.delete(new Query(Criteria.where("_id").is(file.getGridFsIconId())));
        gridFsTemplate.delete(new Query(Criteria.where("_id").is(file.getGridFsId())));

        fileRepository.deleteById(fileId.getValue());
    }

    InputStream getContent(String gridFsId) {
        if (gridFsId == null) {
            return null;
        }
        GridFSFile gridFsFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(gridFsId)));
        GridFsResource resource = gridFsTemplate.getResource(gridFsFile);
        try {
            return resource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
