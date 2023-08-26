package cloud.adapter.api.api;

import cloud.adapter.api.mapper.AddFileRequestMapper;
import cloud.adapter.api.mapper.FileResponseMapper;
import cloud.adapter.api.model.AddFileRequest;
import cloud.adapter.api.model.AddFileResponse;
import cloud.adapter.api.model.AddFolderRequest;
import cloud.adapter.api.model.FileListElementResponse;
import cloud.application.model.File;
import cloud.application.ports.in.AddFileUseCase;
import cloud.application.ports.in.GetFileUseCase;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
public record FilesController(AddFileRequestMapper addFileRequestMapper,
                              FileResponseMapper fileResponseMapper,
                              AddFileUseCase addFileUseCase,
                              GetFileUseCase getFileUseCase) {

    @CrossOrigin
    @PostMapping(path = "/file", consumes = {MULTIPART_FORM_DATA_VALUE}, produces = {APPLICATION_JSON_VALUE})
    AddFileResponse initFile(@ModelAttribute AddFileRequest addFileRequest) throws IOException {
        File fileResponse = addFileUseCase.addFile(addFileRequestMapper.mapToFile(addFileRequest));
        return new AddFileResponse(fileResponseMapper.mapToFileListElementResponse(fileResponse));
    }

    @CrossOrigin
    @PostMapping(path = "/folder", consumes = MediaType.APPLICATION_JSON_VALUE)
    AddFileResponse initFolder(@RequestBody AddFolderRequest addFolderRequest) throws IOException {
        File fileResponse = addFileUseCase.addFile(addFileRequestMapper.mapToFile(addFolderRequest));
        return new AddFileResponse(fileResponseMapper.mapToFileListElementResponse(fileResponse));
    }

    @CrossOrigin
    @GetMapping(path = "/file", produces = {APPLICATION_JSON_VALUE})
    List<FileListElementResponse> initFile(@RequestParam String path) throws IOException {
        return fileResponseMapper.mapToFileListElementResponseList(getFileUseCase.getByPath(path));
    }

}
