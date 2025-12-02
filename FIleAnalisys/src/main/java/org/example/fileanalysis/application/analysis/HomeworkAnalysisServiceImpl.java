package org.example.fileanalysis.application.analysis;

import org.example.fileanalysis.application.dto.PlagiatorsReport;
import org.example.fileanalysis.application.dto.ReportResponse;
import org.example.fileanalysis.domain.model.Homework;
import org.example.fileanalysis.domain.model.Plagiarism;
import org.example.fileanalysis.domain.model.User;
import org.example.fileanalysis.domain.repository.HomeworkRepository;
import org.example.fileanalysis.domain.repository.PlagiarismRepository;
import org.example.fileanalysis.domain.service.PlagiarismChecker;
import org.example.fileanalysis.infrastructure.persistence.s3.YandexS3Service;
import org.example.fileanalysis.infrastructure.web.analysis.WordCloudClientImpl;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HomeworkAnalysisServiceImpl implements HomeworkAnalysisService {

    private final HomeworkRepository homeworkRepository;
    private final HomeworkStorage homeworkStorage;
    private final PlagiarismChecker checker;
    private final PlagiarismRepository plagiarismRepository;
    private final WordCloudClient wordCloudClient;

    public HomeworkAnalysisServiceImpl(HomeworkRepository homeworkRepository, HomeworkStorage homeworkStorage, PlagiarismRepository plagiarismRepository, PlagiarismChecker checker, WordCloudClient wordCloudClient) {
        this.homeworkRepository = homeworkRepository;
        this.homeworkStorage = homeworkStorage;
        this.plagiarismRepository = plagiarismRepository;
        this.checker = checker;
        this.wordCloudClient = wordCloudClient;
    }

    @Override
    public void analysis(UUID homeworkId) throws IOException {
        Homework homework = homeworkRepository.getHomeWorkById(homeworkId);
        List<Homework> others = homeworkRepository.getHomeworksByTask(homework.getTask());
        String homeworkAsText = homeworkStorage.getHomeworkAsText(homework);

        Resource image = wordCloudClient.getWorkCLoud(homeworkAsText);
        homeworkStorage.storeWordCloudOfHomework(homework, image);
        System.out.println("Изображение получено: " + image.getFilename() + " ");

        for (Homework other : others) {
            if (other.getAuthor().equals(homework.getAuthor())) continue;
            String text = homeworkStorage.getHomeworkAsText(homework);
            if (checker.isPlagiarized(homeworkAsText, text)) {
                plagiarismRepository.savePlagiarism(new Plagiarism(homework.getAuthor(), other.getAuthor(), other.getTask()));
            }
        }
    }

    @Override
    public ReportResponse getTaskReport(String task) {
        List<Plagiarism> plagiarisms = plagiarismRepository.getPlagiarismByTask(task);
        List<PlagiatorsReport> list = new ArrayList<>();

        int groupId = 0;
        for (Plagiarism plagiarism : plagiarisms) {
            boolean isExists = false;
            int saveIndex = 0;
            for (int i = 0; i < list.size(); ++i) {
                if (list.get(i).getPlagiators().contains(plagiarism.getUser1())) {
                    isExists = true;
                    saveIndex = i;
                    break;
                }
            }

            if (!isExists) {
                list.add(new PlagiatorsReport(groupId++, new HashSet<>()));
                list.get(list.size() - 1).getPlagiators().add(plagiarism.getUser1());
                list.get(list.size() - 1).getPlagiators().add(plagiarism.getUser2());
            } else {
                list.get(saveIndex).getPlagiators().add(plagiarism.getUser2());
            }
        }
        return new ReportResponse(list, task);
    }
}
