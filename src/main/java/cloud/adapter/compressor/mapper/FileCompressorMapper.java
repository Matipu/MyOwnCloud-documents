package cloud.adapter.compressor.mapper;

import org.mapstruct.Mapper;

import java.io.IOException;
import java.io.InputStream;

@Mapper
public abstract class FileCompressorMapper {

    public byte[] inputStreamToStringMapper(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        return inputStream.readAllBytes();
    }
}
