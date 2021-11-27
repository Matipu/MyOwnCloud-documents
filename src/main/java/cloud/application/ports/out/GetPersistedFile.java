package cloud.application.ports.out;

import cloud.application.model.File;
import cloud.application.model.FileId;

public interface GetPersistedFile {

    File getFile(FileId fileId);
}
