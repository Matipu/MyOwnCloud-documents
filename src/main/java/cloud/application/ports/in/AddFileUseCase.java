package cloud.application.ports.in;

import cloud.application.model.File;
import cloud.application.model.FileId;

public interface AddFileUseCase {

    FileId addFile(File file);
}
