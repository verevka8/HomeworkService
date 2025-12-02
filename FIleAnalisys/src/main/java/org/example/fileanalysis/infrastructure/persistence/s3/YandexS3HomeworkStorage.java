package org.example.fileanalysis.infrastructure.persistence.s3;


import org.example.fileanalysis.application.analysis.HomeworkStorage;
import org.example.fileanalysis.domain.model.Homework;
import org.example.fileanalysis.infrastructure.persistence.s3.key.HomeworkKeyFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Repository
public class YandexS3HomeworkStorage implements HomeworkStorage {
    private final YandexS3Service s3Service;
    private final HomeworkKeyFactory keyFactory;

    public YandexS3HomeworkStorage(YandexS3Service s3Service, HomeworkKeyFactory keyFactory) {
        this.s3Service = s3Service;
        this.keyFactory = keyFactory;
    }

    @Override
    public void storeWordCloudOfHomework(Homework homework, Resource file) throws IOException {
        s3Service.upload(keyFactory.buildKeyForWordCloud(homework), file);
    }

    @Override
    public String getHomeworkAsText(Homework homework) {
        return s3Service.getObjectAsText(keyFactory.buildKeyForHomeworkFile(homework));
    }
}
