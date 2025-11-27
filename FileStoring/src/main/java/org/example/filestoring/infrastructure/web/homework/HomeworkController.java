package org.example.filestoring.infrastructure.web.homework;

import org.example.filestoring.application.homework.HomeworkService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class HomeworkController {
    private final HomeworkService service;

    public HomeworkController(HomeworkService service){
        this.service = service;
    }
    @PostMapping(value = "/homework", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void upload(@RequestPart("data") UploadHomeworkRequest data, @RequestPart("file") MultipartFile file) throws IOException {
        service.createHomework(data.author(),data.task(), file);
    }
}
