package cloud.application.ports.in;

import cloud.application.model.File;
import cloud.application.model.FileId;

public interface GetFileUseCase {

    File getFile(FileId fileId);
}