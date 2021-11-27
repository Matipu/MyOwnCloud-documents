package cloud.application.ports.in;

import cloud.application.model.FileId;

public interface DeleteFileUseCase {

    void deleteFile(FileId id);
}
