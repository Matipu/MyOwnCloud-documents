package cloud.adapter.api.mapper;

import cloud.adapter.api.model.AddFileRequest;
import cloud.adapter.api.model.FileListElementResponse;
import cloud.application.model.File;
import cloud.application.model.FileId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Mapper
public interface AddFileRequestMapper {

    @Mapping(target = "name", expression = "java(addFileRequest.getFile().getOriginalFilename())")
    @Mapping(target = "content", expression = "java(addFileRequest.getFile().getInputStream())")
    @Mapping(target = "contentType", expression = "java(addFileRequest.getFile().getContentType())")
    @Mapping(target = "iconContent", expression = "java(addFileRequest.getIcon().getInputStream())")
    @Mapping(target = "fileId", ignore = true)
    File mapToFile(AddFileRequest addFileRequest) throws IOException;

    List<FileListElementResponse> mapToFileListElementResponseList(List<File> allFiles) throws IOException;

    @Mapping(target = "id", source = "fileId")
    FileListElementResponse mapToFileListElementResponse(File file) throws IOException;

    default byte[] InputStreamToStringMapper(InputStream inputStream) throws IOException {
        return inputStream.readAllBytes();
    }

    default FileId mapStringToFileId(String value) {
        return FileId.of(value);
    }

    default String mapFileIdToString(FileId fileId) {
        return fileId.getValue();
    }
}
