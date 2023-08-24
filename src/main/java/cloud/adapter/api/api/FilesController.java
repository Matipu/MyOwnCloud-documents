package cloud.adapter.api.api;

import cloud.adapter.api.mapper.AddFileRequestMapper;
import cloud.adapter.api.model.AddFileRequest;
import cloud.adapter.api.model.AddFileResponse;
import cloud.adapter.api.model.FileListElementResponse;
import cloud.application.model.FileId;
import cloud.application.ports.in.AddFileUseCase;
import cloud.application.ports.in.GetFileUseCase;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
public record FilesController(AddFileRequestMapper addFileRequestMapper,
                              AddFileUseCase addFileUseCase,
                              GetFileUseCase getFileUseCase) {

    @CrossOrigin
    @PostMapping(path = "/file", consumes = {MULTIPART_FORM_DATA_VALUE}, produces = {APPLICATION_JSON_VALUE})
    AddFileResponse initFile(@ModelAttribute AddFileRequest addFileRequest) throws IOException {
        FileId fileId = addFileUseCase.addFile(addFileRequestMapper.mapToFile(addFileRequest));
        return new AddFileResponse(fileId.getValue());
    }

    @CrossOrigin
    @GetMapping(path = "/file", produces = {APPLICATION_JSON_VALUE})
    List<FileListElementResponse> initFile() throws IOException {
        return addFileRequestMapper.mapToFileListElementResponseList(getFileUseCase.getAllFiles());
    }

}
