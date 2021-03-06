package cloud.adapter.api.mapper;

import cloud.adapter.api.model.AddFileRequest;
import cloud.adapter.api.model.AddFolderRequest;
import cloud.application.model.File;
import cloud.application.model.FileId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.io.IOException;
import java.io.InputStream;

import static cloud.application.model.File.FOLDER_CONTENT_TYPE;

@Mapper
public interface AddFileRequestMapper {

    @Mapping(target = "name", expression = "java(addFileRequest.getFile().getOriginalFilename())")
    @Mapping(target = "content", expression = "java(addFileRequest.getFile().getInputStream())")
    @Mapping(target = "contentType", expression = "java(addFileRequest.getFile().getContentType())")
    @Mapping(target = "iconContent", expression = "java(getIconContent(addFileRequest))")
    @Mapping(target = "fileId", ignore = true)
    File mapToFile(AddFileRequest addFileRequest) throws IOException;

    @Mapping(target = "contentType", expression = "java(getFolderContentType())")
    File mapToFile(AddFolderRequest addFolderRequest) throws IOException;

    default FileId mapStringToFileId(String value) {
        return FileId.of(value);
    }

    default String mapFileIdToString(FileId fileId) {
        return fileId.getValue();
    }

    default String getFolderContentType() {
        return FOLDER_CONTENT_TYPE;
    }

    default InputStream getIconContent(AddFileRequest addFileRequest) throws IOException {
        return addFileRequest.getIcon() == null ? null : addFileRequest.getIcon().getInputStream();
    }
}
