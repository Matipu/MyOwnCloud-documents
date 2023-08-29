package cloud.adapter.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@AllArgsConstructor
public class FileEntity {

    @Id
    private String id;
    @Setter
    private String path;
    @Setter
    private String name;
    private String gridFsId;
    private String gridFsIconId;
    private String contentType;
}
