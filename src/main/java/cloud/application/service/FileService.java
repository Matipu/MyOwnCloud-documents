package cloud.application.service;

import cloud.application.model.File;
import cloud.application.model.FileId;
import cloud.application.ports.in.AddFileUseCase;
import cloud.application.ports.in.DeleteFileUseCase;
import cloud.application.ports.in.GetFileUseCase;
import cloud.application.ports.in.UpdateFileUseCase;
import cloud.application.ports.out.*;

import java.util.List;
import java.util.regex.Pattern;

public record FileService(SaveFile saveFile, GetPersistedFile getPersistedFile, RemovePersistedFile removePersistedFile,
                          UpdatePersistedFile updatePersistedFile, FileCompressor fileCompressor)
        implements AddFileUseCase, GetFileUseCase, DeleteFileUseCase, UpdateFileUseCase {

    @Override
    public File addFile(File file) {
        file.setName(computeFileName(file.getPath(), file.getName()));
        file.setFileId(generateFileId());
        saveFile.saveFile(file);

        return file;
    }

    @Override
    public List<File> getByPath(String path) {
        return getPersistedFile.getFilesByPath(path);
    }

    @Override
    public File getFileToDownload(FileId fileId) {
        File file = getPersistedFile.getFile(fileId);
        if (file.isFolder()) {
            List<File> childFiles = getFilesInFolder(file.getPath(), file.getName());
            return fileCompressor.compressFilesToZip(childFiles, file.getName() + ".zip", file.getPath());
        }
        file.setContent(getPersistedFile.getFileContent(fileId));
        return file;
    }

    @Override
    public void updateFile(FileId id, String name) {
        File file = getPersistedFile.getFile(id);
        final String computedName = computeFileName(file.getPath(), name);
        if (file.isFolder()) {
            List<File> childFiles = getFilesInFolder(file.getPath(), file.getName());
            childFiles.forEach((childFile) -> {
                changeParentFolderName(childFile, file.getPath() + file.getName(), computedName);
                updatePersistedFile.updateFile(childFile);
            });
        }
        file.setName(computedName);
        updatePersistedFile.updateFile(file);
    }

    private List<File> getFilesInFolder(String path, String name) {
        String pathFolderNamePattern = Pattern.quote(path + name);
        return getPersistedFile.getFilesByPathRegex(pathFolderNamePattern);
    }

    @Override
    public void deleteFile(FileId id) {
        File file = getPersistedFile.getFile(id);
        if (file.isFolder()) {
            List<FileId> fileIds = getPersistedFile.getFileIdsByPathRegex(Pattern.quote(file.getPath() + file.getName()) + "/.*");
            fileIds.forEach(removePersistedFile::removeFile);
        }
        removePersistedFile.removeFile(id);

    }

    private void changeParentFolderName(File file, String changedPath, String newFolderName) {
        String newPath = changedPath.substring(0, changedPath.lastIndexOf("/")) + "/" + newFolderName;

        file.setPath(file.getPath().replaceFirst(Pattern.quote(changedPath), newPath));
    }

    private String computeFileName(String path, String originalFileName) {
        List<File> filesInActualFolder = getPersistedFile.getFilesByPath(path);
        int postfix = 1;
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
