package cloud.adapter.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.InputStream;

@Document
@Getter
@AllArgsConstructor
public class FileEntity {

    @Id
    private String id;
    private String path;
    private String name;
    private String gridFsId;
    private String gridFsIconId;
    private String contentType;
    //private InputStream content;
}
