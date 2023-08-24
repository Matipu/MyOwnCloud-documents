package cloud.application.ports.out;

import cloud.application.model.File;
import cloud.application.model.FileId;

import java.util.List;

public interface GetPersistedFile {

    File getFile(FileId fileId);

    List<File> getAllFiles();
}
