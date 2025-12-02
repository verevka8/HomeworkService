package org.example.filestoring.application.homework;

import org.example.filestoring.domain.model.Homework;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface HomeworkStorage {
    void store(Homework homework, MultipartFile file) throws IOException;
}
