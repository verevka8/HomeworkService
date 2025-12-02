package org.example.fileanalysis.application.analysis;


import org.example.fileanalysis.domain.model.Homework;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.IOException;
import java.util.List;

public interface HomeworkStorage {
    void storeWordCloudOfHomework(Homework homework, Resource file) throws IOException;

    String getHomeworkAsText(Homework homework);
}
