package cloud.adapter.api.mapper;

import cloud.adapter.api.model.AddFileRequest;
import cloud.application.model.File;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.io.IOException;

@Mapper
public interface AddFileRequestMapper {

    @Mapping(target = "name", expression = "java(addFileRequest.getFile().getName())")
    @Mapping(target = "content", expression = "java(addFileRequest.getFile().getInputStream())")
    @Mapping(target = "contentType", expression = "java(addFileRequest.getFile().getContentType())")
    @Mapping(target = "fileId", ignore = true)
    File mapToFile(AddFileRequest addFileRequest) throws IOException;
}
