package cloud.adapter.controllers.controler;

import cloud.adapter.controllers.mapper.AddFileRequestMapper;
import cloud.adapter.controllers.model.AddFileRequest;
import cloud.application.ports.in.AddFileUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilesController {

    @Autowired
    AddFileRequestMapper addFileRequestMapper;
    @Autowired
    AddFileUseCase addFileUseCase;

    @PostMapping("/file")
    void createMessage(@RequestBody AddFileRequest addFileRequest) {
        addFileUseCase.addFile(addFileRequestMapper.mapToFile(addFileRequest));
    }
}
