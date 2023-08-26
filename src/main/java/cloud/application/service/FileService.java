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
    public File addFile(File file) {
        file.setName(computeFileName(file));
        file.setFileId(generateFileId());
        saveFile.saveFile(file);

        return file;
    }

    @Override
    public List<File> getByPath(String path) {
        return getPersistedFile.getFilesByPath(path);
    }

    @Override
    public File getFile(FileId fileId) {
        return getPersistedFile.getFile(fileId);
    }

    private String computeFileName(File file) {
        List<File> filesInActualFolder = getPersistedFile.getFilesByPath(file.getPath());
        int postfix = 1;
        String originalFileName = file.getName();
        String fileName = originalFileName;
        while (isFileNameExistsInFolder(fileName, filesInActualFolder)) {
            fileName = originalFileName + " (" + postfix + ")";
            postfix++;
        }

        return fileName;
    }

    private boolean isFileNameExistsInFolder(String fileName, List<File> filesInActualFolder) {
        for (File fileInFolder : filesInActualFolder) {
            if (fileInFolder.getName().equals(fileName)) {
                return true;
            }
        }
        return false;
    }

    private FileId generateFileId() {
        FileId fileId = new FileId();
        fileId.generateUUIDValue();
        return fileId;
    }
}
