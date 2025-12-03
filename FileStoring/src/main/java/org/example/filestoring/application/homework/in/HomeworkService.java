package org.example.filestoring.application.homework.in;

import org.example.filestoring.domain.model.Homework;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface HomeworkService {
    Homework createHomework(UUID authorId, String task, MultipartFile file) throws IOException;
    Homework getHomeworkById(UUID homeworkId);
}
