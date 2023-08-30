package cloud.application.ports.out;

import cloud.application.model.File;

import java.util.List;

public interface FileCompressor {

    File compressFilesToZip(List<File> files, String fileName, String actualPath);
}
