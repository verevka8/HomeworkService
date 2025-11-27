package org.example.fileanalisys.application.homework;

import org.example.fileanalisys.application.user.UserService;
import org.example.fileanalisys.domain.model.Homework;
import org.example.fileanalisys.domain.model.User;
import org.example.fileanalisys.domain.repository.HomeworkRepository;
import org.example.fileanalisys.infrastructure.persistence.s3.YandexS3Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Service
public class HomeworkImpl implements HomeworkService {
    @Value("${file.upload-dir}")
    private String uploadDir;

    private final YandexS3Service s3Service;
    private final UserService userService;
    private final HomeworkRepository homeworkRepository;

    public HomeworkImpl(YandexS3Service s3Service, UserService userService, HomeworkRepository homeworkRepository){
        this.s3Service = s3Service;
        this.userService = userService;
        this.homeworkRepository = homeworkRepository;
    }

    @Override
    public Homework createHomework(UUID authorId, String task, MultipartFile file) throws IOException {
        User user = userService.getUserById(authorId);
        Homework homework = Homework.createNew(user, new Date(), task, file.getOriginalFilename());
        homeworkRepository.saveHomeWork(homework);
        System.out.println(file.getOriginalFilename());
        s3Service.upload(homework, file);
        return homework;
    }

    @Override
    public Homework getHomeworkById(UUID homeworkId) {
        return null;
    }
}
