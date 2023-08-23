package cloud.adapter.api.api;

import cloud.adapter.api.mapper.AddFileRequestMapper;
import cloud.adapter.api.model.AddFileRequest;
import cloud.adapter.api.model.AddFileResponse;
import cloud.application.model.FileId;
import cloud.application.ports.in.AddFileUseCase;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
public record FilesController(AddFileRequestMapper addFileRequestMapper,
                              AddFileUseCase addFileUseCase) {

    @CrossOrigin
    @PostMapping(path = "/file", consumes = {MULTIPART_FORM_DATA_VALUE}, produces = {APPLICATION_JSON_VALUE})
    AddFileResponse initFile(@ModelAttribute AddFileRequest addFileRequest) throws IOException {
        FileId fileId = addFileUseCase.addFile(addFileRequestMapper.mapToFile(addFileRequest));
        return new AddFileResponse(fileId.getValue());
    }

}
