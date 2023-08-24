package cloud.application.service;

import cloud.application.model.File;
import cloud.application.model.FileId;
import cloud.application.ports.in.AddFileUseCase;
import cloud.application.ports.in.GetFileUseCase;
import cloud.application.ports.out.GetPersistedFile;
import cloud.application.ports.out.SaveFile;

import java.util.List;

public record FileService(SaveFile saveFile,
                          GetPersistedFile getPersistedFile) implements AddFileUseCase, GetFileUseCase {

    @Override
    public FileId addFile(File file) {
        file.setFileId(generateFileId());
        saveFile.saveFile(file);

        return file.getFileId();
    }

    private FileId generateFileId() {
        FileId fileId = new FileId();
        fileId.generateUUIDValue();
        return fileId;
    }

    @Override
    public List<File> getAllFiles() {
        return getPersistedFile.getAllFiles();
    }

    @Override
    public File getFile(FileId fileId) {
        return getPersistedFile.getFile(fileId);
    }
}
