package org.example.fileanalisys.domain.repository;

import org.example.fileanalisys.domain.model.Homework;

import java.util.UUID;

public interface HomeworkRepository {
    void saveHomeWork(Homework homeWork);

    Homework getHomeWorkById(UUID uuid);
}
