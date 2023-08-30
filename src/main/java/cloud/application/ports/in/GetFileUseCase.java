package cloud.application.ports.in;

import cloud.application.model.File;
import cloud.application.model.FileId;

import java.util.List;

public interface GetFileUseCase {

    List<File> getByPath(String path);

    File getFileToDownload(FileId fileId);
}
