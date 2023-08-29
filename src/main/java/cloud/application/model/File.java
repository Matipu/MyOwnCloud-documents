package cloud.application.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;

@Getter
@AllArgsConstructor
public class File {

    public static final String FOLDER_CONTENT_TYPE = "folder";

    @Setter
    private FileId fileId;
    @Setter
    private String path;
    @Setter
    private String name;
    @Setter
    private InputStream content;
    @Setter
    private InputStream iconContent;
    private String contentType;

    public boolean isFolder() {
        return contentType.equals(FOLDER_CONTENT_TYPE);
    }
}
