package cloud.adapter.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileListElementResponse {

    String id;
    String name;
    String contentType;
}
