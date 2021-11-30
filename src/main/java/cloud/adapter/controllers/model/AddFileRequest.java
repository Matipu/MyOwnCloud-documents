package cloud.adapter.controllers.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class AddFileRequest {

    String path;
    String name;
}
