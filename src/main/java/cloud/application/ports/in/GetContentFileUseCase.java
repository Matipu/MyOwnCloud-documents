package cloud.application.ports.in;

import cloud.application.model.FileId;

import java.io.InputStream;

public interface GetContentFileUseCase {

    InputStream getFileIcon(FileId fileId);

    InputStream getFileContent(FileId fileId);
}
