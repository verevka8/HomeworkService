package org.example.fileanalysis.domain.repository;

import org.example.fileanalysis.domain.model.Homework;

import java.util.List;
import java.util.UUID;

public interface HomeworkRepository {
    void saveHomeWork(Homework homeWork);

    Homework getHomeWorkById(UUID uuid);
    List<Homework> getHomeworksByTask(String task);
}
