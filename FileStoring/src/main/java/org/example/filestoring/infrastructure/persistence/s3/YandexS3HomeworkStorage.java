package org.example.filestoring.infrastructure.persistence.s3;

import org.example.filestoring.application.homework.HomeworkStorage;
import org.example.filestoring.domain.model.Homework;
import org.example.filestoring.infrastructure.persistence.s3.key.HomeworkKeyFactory;
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
    public void store(Homework homework, MultipartFile file) throws IOException {
        String key = keyFactory.buildKey(homework);
        s3Service.uploadFile(key, file);
    }
}
