package org.example.fileanalisys.infrastructure.persistence.mapper;

import org.example.fileanalisys.domain.model.Homework;
import org.example.fileanalisys.infrastructure.persistence.jpa.entity.HomeworkEntity;

public interface HomeworkMapper {
    HomeworkEntity toEntity(Homework homeWork);

    Homework toDomain(HomeworkEntity homeWorkEntity);
}
