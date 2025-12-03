package org.example.filestoring.domain.repository;

import org.example.filestoring.domain.model.Homework;

import java.util.Optional;
import java.util.UUID;

public interface HomeworkRepository {
    void saveHomeWork(Homework homeWork);

    Optional<Homework> getHomeWorkById(UUID uuid);
}
