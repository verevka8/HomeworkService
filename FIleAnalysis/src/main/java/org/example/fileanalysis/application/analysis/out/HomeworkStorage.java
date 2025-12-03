package org.example.fileanalysis.application.analysis.out;


import org.example.fileanalysis.domain.model.Homework;
import org.springframework.core.io.Resource;

import java.io.IOException;

public interface HomeworkStorage {
    void storeWordCloudOfHomework(Homework homework, Resource file) throws IOException;

    String getHomeworkAsText(Homework homework);
}
