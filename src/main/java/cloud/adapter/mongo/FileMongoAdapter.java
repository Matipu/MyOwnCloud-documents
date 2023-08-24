package cloud.adapter.mongo;

import cloud.adapter.mongo.mapper.FileEntityMapper;
import cloud.adapter.mongo.model.FileEntity;
import cloud.adapter.mongo.repository.FileRepository;
import cloud.application.model.File;
import cloud.application.model.FileId;
import cloud.application.ports.out.GetPersistedFile;
import cloud.application.ports.out.SaveFile;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
        List<FileEntity> fileEntities = fileRepository.findAll();
        List<File> files = new ArrayList<>();
        for(FileEntity fileEntity : fileEntities) {
            File file = fileEntityMapper.mapFileEntityToFile(fileEntity);
            file.setIconContent(getIconContent(fileEntity.getGridFsIconId()));
            files.add(file);
        }
        return files;
    }

    public InputStream getIconContent(String gridFsIconId) {
        if(gridFsIconId == null) {
            return null;
        }
        GridFSFile gridFsFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(gridFsIconId)));
        GridFsResource resource = gridFsTemplate.getResource(gridFsFile.getFilename());
        try {
            return resource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
