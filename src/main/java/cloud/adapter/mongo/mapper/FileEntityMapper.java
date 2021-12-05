package cloud.adapter.mongo.mapper;

import cloud.adapter.mongo.model.FileEntity;
import cloud.application.model.File;
import cloud.application.model.FileId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface FileEntityMapper {

    @Mapping(source = "file.fileId", target = "id")
    FileEntity mapFileToFileEntity(File file, String gridFsId, String gridFsIconId);

    @Mapping(source = "id", target = "fileId")
    File mapFileEntityToFile(FileEntity fileEntity);

    default FileId mapStringToFileId(String value) {
        return FileId.of(value);
    }

    default String mapFileIdToString(FileId fileId) {
        return fileId.getValue();
    }
}
