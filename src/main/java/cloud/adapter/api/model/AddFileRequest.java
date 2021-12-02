package cloud.adapter.api.model;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class AddFileRequest {

    MultipartFile file;
}
