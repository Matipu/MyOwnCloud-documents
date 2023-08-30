package cloud.adapter.compressor;

import cloud.adapter.compressor.mapper.FileCompressorMapper;
import cloud.application.model.File;
import cloud.application.ports.in.GetContentFileUseCase;
import cloud.application.ports.out.FileCompressor;
import lombok.SneakyThrows;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public record FileCompressorAdapter(FileCompressorMapper fileCompressorMapper,
                                    GetContentFileUseCase getContentFileUseCase) implements FileCompressor {

    @SneakyThrows
    @Override
    public File compressFilesToZip(List<File> files, String fileName, String actualPath) {
        FileOutputStream fos = new FileOutputStream(fileName);
        ZipOutputStream zos = new ZipOutputStream(fos);

        for (File file : files) {
            if (file.isFolder()) {
                continue;
            }
            String path = file.getPath().substring(actualPath.length());
            zos.putNextEntry(new ZipEntry(path + file.getName()));
            InputStream fileContent = getContentFileUseCase.getFileContent(file.getFileId());
            byte[] bytes = fileCompressorMapper.inputStreamToStringMapper(fileContent);
            zos.write(bytes, 0, bytes.length);
            zos.closeEntry();
        }
        zos.close();

        final InputStream targetStream = new DataInputStream(new FileInputStream(fileName));

        return new File(null, null, fileName, targetStream, null, "application/zip");
    }
}
