package cloud.application.ports.out;

import cloud.application.model.File;

public interface UpdatePersistedFile {

    void updateFile(File file);
}
