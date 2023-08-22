package cloud.application.service;

import cloud.application.model.File;
import cloud.application.model.FileId;
import cloud.application.ports.in.AddFileUseCase;
import cloud.application.ports.out.SaveFile;

public class FileService implements AddFileUseCase {

    SaveFile saveFile;

    @Override
    public FileId addFile(File file) {
        return saveFile.saveFile(file);
    }
}
