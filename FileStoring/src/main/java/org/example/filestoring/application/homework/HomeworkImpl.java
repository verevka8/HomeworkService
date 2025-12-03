package org.example.filestoring.application.homework;

import org.example.filestoring.application.exception.HomeworkNotFoundException;
import org.example.filestoring.application.homework.in.HomeworkService;
import org.example.filestoring.application.homework.out.HomeworkAnalisysClient;
import org.example.filestoring.application.homework.out.HomeworkStorage;
import org.example.filestoring.application.user.in.UserService;
import org.example.filestoring.domain.model.Homework;
import org.example.filestoring.domain.model.User;
import org.example.filestoring.domain.repository.HomeworkRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Service
public class HomeworkImpl implements HomeworkService {
    private final HomeworkStorage homeworkStorage;
    private final UserService userService;
    private final HomeworkRepository homeworkRepository;
    private final HomeworkAnalisysClient homeworkAnalisys;

    public HomeworkImpl(HomeworkStorage homeworkStorage, UserService userService, HomeworkRepository homeworkRepository, HomeworkAnalisysClient homeworkAnalisys){
        this.homeworkStorage = homeworkStorage;
        this.userService = userService;
        this.homeworkRepository = homeworkRepository;
        this.homeworkAnalisys = homeworkAnalisys;
    }

    @Override
    public Homework createHomework(UUID authorId, String task, MultipartFile file) throws IOException {
        User user = userService.getUserById(authorId);
        Homework homework = Homework.createNew(user, new Date(), task, file.getOriginalFilename());
        homeworkRepository.saveHomeWork(homework);
        System.out.println(file.getOriginalFilename());
        homeworkStorage.store(homework, file);
        homeworkAnalisys.analyzeHomework(homework.getUuid());
        return homework;
    }

    @Override
    public Homework getHomeworkById(UUID homeworkId) {
        return homeworkRepository.getHomeWorkById(homeworkId).orElseThrow(HomeworkNotFoundException::new);
    }
}
