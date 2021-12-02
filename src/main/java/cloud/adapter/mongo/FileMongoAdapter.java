package cloud.adapter.mongo;

import cloud.application.model.File;
import cloud.application.model.FileId;
import cloud.application.ports.in.AddFileUseCase;
import cloud.application.ports.out.SaveFile;

public class FileMongoAdapter implements SaveFile {


    @Override
    public FileId saveFile(File file) {
        return null;
    }
}
