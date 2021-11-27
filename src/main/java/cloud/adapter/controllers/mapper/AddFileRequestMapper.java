package cloud.adapter.controllers.mapper;

import cloud.adapter.controllers.model.AddFileRequest;
import cloud.application.model.File;
import org.mapstruct.Mapper;

@Mapper
public interface AddFileRequestMapper {

    File mapToFile(AddFileRequest addFileRequest);
}
