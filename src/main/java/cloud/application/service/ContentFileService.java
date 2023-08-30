package cloud.application.service;

import cloud.application.model.FileId;
import cloud.application.ports.in.GetContentFileUseCase;
import cloud.application.ports.out.GetPersistedFile;

import java.io.InputStream;

public record ContentFileService(GetPersistedFile getPersistedFile)
        implements GetContentFileUseCase {

    @Override
    public InputStream getFileIcon(FileId fileId) {
        return getPersistedFile.getFileIcon(fileId);
    }

    @Override
    public InputStream getFileContent(FileId fileId) {
        return getPersistedFile.getFileContent(fileId);
    }
}
