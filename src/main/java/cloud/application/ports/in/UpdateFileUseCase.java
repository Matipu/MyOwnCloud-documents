package cloud.application.ports.in;

import cloud.application.model.FileId;

public interface UpdateFileUseCase {

    void updateFile(FileId id, String name);
}
