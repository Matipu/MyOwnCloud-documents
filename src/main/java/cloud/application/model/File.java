package cloud.application.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;

@Getter
@AllArgsConstructor
public class File {

    @Setter
    private FileId fileId;
    private String path;
    private String name;
    private InputStream content;
    private InputStream iconContent;
    private String contentType;

}
