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
public abstract class FileResponseMapper {

    public abstract List<FileListElementResponse> mapToFileListElementResponseList(List<File> allFiles) throws IOException;

    @Mapping(target = "id", source = "fileId")
    public abstract FileListElementResponse mapToFileListElementResponse(File file) throws IOException;

    public byte[] inputStreamToStringMapper(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        return inputStream.readAllBytes();
    }

    FileId mapStringToFileId(String value) {
        return FileId.of(value);
    }

    String mapFileIdToString(FileId fileId) {
        return fileId.getValue();
    }
}
