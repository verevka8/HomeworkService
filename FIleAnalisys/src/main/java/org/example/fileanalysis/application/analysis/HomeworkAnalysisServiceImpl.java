package org.example.fileanalysis.application.analysis;

import org.example.fileanalysis.domain.model.Homework;
import org.example.fileanalysis.domain.model.Plagiarism;
import org.example.fileanalysis.domain.repository.HomeworkRepository;
import org.example.fileanalysis.domain.repository.PlagiarismRepository;
import org.example.fileanalysis.domain.service.PlagiarismChecker;
import org.example.fileanalysis.infrastructure.persistence.s3.YandexS3Service;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HomeworkAnalysisServiceImpl implements HomeworkAnalysisService {

    private final HomeworkRepository homeworkRepository;
    private final YandexS3Service s3Service;
    private final PlagiarismChecker checker;
    private final PlagiarismRepository plagiarismRepository;

    public HomeworkAnalysisServiceImpl(HomeworkRepository homeworkRepository, YandexS3Service s3Service, PlagiarismRepository plagiarismRepository, PlagiarismChecker checker) {
        this.homeworkRepository = homeworkRepository;
        this.s3Service = s3Service;
        this.plagiarismRepository = plagiarismRepository;
        this.checker = checker;
    }

    @Override
    public void analysis(UUID homeworkId) {
        Homework homework = homeworkRepository.getHomeWorkById(homeworkId);
        List<Homework> others = homeworkRepository.getHomeworksByTask(homework.getTask());
        String homeworkAsText = s3Service.getObjectAsText(homework.getTask() + "/" + homework.getAuthor().getUuid() + "/" + homework.getFilename()); //TODO: убрать хардкор

        for (Homework other : others) {
            if (other.getAuthor().equals(homework.getAuthor())) continue;
            String text = s3Service.getObjectAsText(other.getTask() + "/" + other.getAuthor().getUuid() + "/" + other.getFilename()); //TODO: убрать хардкор
            if (checker.isPlagiarized(homeworkAsText, text)){
                plagiarismRepository.savePlagiarism(new Plagiarism(homework.getAuthor(), other.getAuthor(), other.getTask()));
            }
        }
    }
}
