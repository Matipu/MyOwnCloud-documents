package cloud.application.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class FileId {

    private String value;

    public void generateUUIDValue() {
        value = UUID.randomUUID().toString();
    }
}
