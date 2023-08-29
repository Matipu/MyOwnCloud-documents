package cloud.application.ports.out;

import cloud.application.model.File;
import cloud.application.model.FileId;

import java.io.InputStream;
import java.util.List;

public interface GetPersistedFile {

    File getFile(FileId fileId);

    InputStream getFileContent(FileId fileId);

    List<File> getFilesByPath(String path);

    List<FileId> getFilesByPathRegex(String pathRegex);
}
