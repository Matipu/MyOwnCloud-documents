package cloud.application.service;

import cloud.application.model.File;
import cloud.application.model.FileId;
import cloud.application.ports.in.AddFileUseCase;
import cloud.application.ports.out.SaveFile;

public record FileService(SaveFile saveFile) implements AddFileUseCase {

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
}
