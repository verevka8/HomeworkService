package org.example.filestoring.infrastructure.persistence.mapper;

import org.example.filestoring.domain.model.Homework;
import org.example.filestoring.infrastructure.persistence.jpa.entity.HomeworkEntity;

public interface HomeworkMapper {
    HomeworkEntity toEntity(Homework homeWork);

    Homework toDomain(HomeworkEntity homeWorkEntity);
}
