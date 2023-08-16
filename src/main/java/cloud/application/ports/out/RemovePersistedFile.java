package cloud.application.ports.out;

import cloud.application.model.FileId;

public interface RemovePersistedFile {

    void removeFile(FileId fileId);
}
