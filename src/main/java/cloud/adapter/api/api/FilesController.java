package cloud.adapter.api.api;

import cloud.adapter.api.mapper.AddFileRequestMapper;
import cloud.adapter.api.mapper.FileResponseMapper;
import cloud.adapter.api.model.*;
import cloud.application.model.File;
import cloud.application.model.FileId;
import cloud.application.ports.in.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
public record FilesController(AddFileRequestMapper addFileRequestMapper,
                              FileResponseMapper fileResponseMapper,
                              AddFileUseCase addFileUseCase,
                              GetFileUseCase getFileUseCase,
                              GetContentFileUseCase getContentFileUseCase,
                              DeleteFileUseCase deleteFileUseCase,
                              UpdateFileUseCase updateFileUseCase) {

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
    List<FileListElementResponse> getFilesByPath(@RequestParam String path) throws IOException {
        return fileResponseMapper.mapToFileListElementResponseList(getFileUseCase.getByPath(path));
    }

    @CrossOrigin
    @GetMapping(path = "/file/content", produces = {APPLICATION_JSON_VALUE})
    FileContentResponse getFileContent(@RequestParam String id) throws IOException {
        byte[] content = fileResponseMapper.inputStreamToStringMapper(getContentFileUseCase.getFileContent(FileId.of(id)));
        return new FileContentResponse(content);
    }

    @CrossOrigin
    @GetMapping(path = "/file/icon", produces = {APPLICATION_JSON_VALUE})
    FileContentResponse getFileIcon(@RequestParam String id) throws IOException {
        byte[] content = fileResponseMapper.inputStreamToStringMapper(getContentFileUseCase.getFileIcon(FileId.of(id)));
        return new FileContentResponse(content);
    }

    @CrossOrigin
    @GetMapping(path = "/file/download")
    public @ResponseBody
    HttpEntity<byte[]> downloadFile(@RequestParam String id, HttpServletResponse response) throws IOException {
        File fileWithContent = getFileUseCase.getFileToDownload(FileId.of(id));
        byte[] content = fileResponseMapper.inputStreamToStringMapper(fileWithContent.getContent());

        HttpHeaders headers = new HttpHeaders();
        headers.add("fileName", fileWithContent.getName());
        headers.add("fileContentType", fileWithContent.getContentType());
        headers.add("Access-Control-Expose-Headers", "*");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new HttpEntity<>(content, headers);
    }

    @CrossOrigin
    @PatchMapping(path = "/file")
    String updateFile(@RequestParam String id, @RequestBody UpdateFileRequest updateFileRequest) {
        updateFileUseCase.updateFile(FileId.of(id), updateFileRequest.getName());
        return "ok";
    }

    @CrossOrigin
    @DeleteMapping(path = "/file")
    String deleteFile(@RequestParam String id) {
        deleteFileUseCase.deleteFile(FileId.of(id));
        return "ok";
    }
}
