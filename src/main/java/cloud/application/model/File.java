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
    @Setter
    private String name;
    @Setter
    private InputStream content;
    @Setter
    private InputStream iconContent;
    private String contentType;

}
