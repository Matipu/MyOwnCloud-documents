package cloud.application.ports.in;

import cloud.application.model.File;
import cloud.application.model.FileId;

import java.io.InputStream;
import java.util.List;

public interface GetFileUseCase {

    List<File> getByPath(String path);

    InputStream getFileIcon(FileId fileId);

    InputStream getFileContent(FileId fileId);
}
