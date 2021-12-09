package cloud.adapter.api.mapper;

import cloud.adapter.api.model.FileListElementResponse;
import cloud.application.model.File;
import cloud.application.model.FileId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Mapper
public interface FileResponseMapper {

    List<FileListElementResponse> mapToFileListElementResponseList(List<File> allFiles) throws IOException;

    @Mapping(target = "id", source = "fileId")
    FileListElementResponse mapToFileListElementResponse(File file) throws IOException;

    default byte[] InputStreamToStringMapper(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        return inputStream.readAllBytes();
    }

    default FileId mapStringToFileId(String value) {
        return FileId.of(value);
    }

    default String mapFileIdToString(FileId fileId) {
        return fileId.getValue();
    }
}
