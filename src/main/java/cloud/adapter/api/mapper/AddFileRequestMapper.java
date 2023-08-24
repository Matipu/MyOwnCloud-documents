package cloud.adapter.api.mapper;

import cloud.adapter.api.model.AddFileRequest;
import cloud.adapter.api.model.FileListElementResponse;
import cloud.application.model.File;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.io.IOException;
import java.util.List;

@Mapper
public interface AddFileRequestMapper {

    @Mapping(target = "name", expression = "java(addFileRequest.getFile().getName())")
    @Mapping(target = "content", expression = "java(addFileRequest.getFile().getInputStream())")
    @Mapping(target = "contentType", expression = "java(addFileRequest.getFile().getContentType())")
    @Mapping(target = "iconContent", expression = "java(addFileRequest.getIcon().getInputStream())")
    @Mapping(target = "fileId", ignore = true)
    File mapToFile(AddFileRequest addFileRequest) throws IOException;

    List<FileListElementResponse> mapToFileListElementResponseList(List<File> allFiles);

    FileListElementResponse mapToFileListElementResponse(File file);
}
