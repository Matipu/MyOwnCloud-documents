package cloud.adapter.api.mapper;

import cloud.adapter.api.model.AddFileRequest;
import cloud.application.model.File;
import org.mapstruct.Mapper;

@Mapper
public interface AddFileRequestMapper {

    File mapToFile(AddFileRequest addFileRequest);
}
