package cloud.adapter.api.api;

import cloud.adapter.api.mapper.AddFileRequestMapper;
import cloud.adapter.api.model.AddFileRequest;
import cloud.adapter.api.model.AddFileResponse;
import cloud.application.model.FileId;
import cloud.application.ports.in.AddFileUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
public class FilesController {

    @Autowired
    AddFileRequestMapper addFileRequestMapper;
    @Autowired
    AddFileUseCase addFileUseCase;

    @CrossOrigin
    @PostMapping(path = "/file", consumes = {MULTIPART_FORM_DATA_VALUE}, produces = {APPLICATION_JSON_VALUE})
    AddFileResponse initFile(@ModelAttribute AddFileRequest addFileRequest) {
        FileId fileId = addFileUseCase.addFile(addFileRequestMapper.mapToFile(addFileRequest));
        return new AddFileResponse(fileId.getValue());
    }

}
