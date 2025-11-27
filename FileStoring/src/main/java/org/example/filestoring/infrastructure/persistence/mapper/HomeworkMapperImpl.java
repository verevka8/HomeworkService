package org.example.filestoring.infrastructure.persistence.mapper;

import org.example.filestoring.domain.model.Homework;
import org.example.filestoring.infrastructure.persistence.jpa.entity.HomeworkEntity;
import org.springframework.stereotype.Component;

@Component
public class HomeworkMapperImpl implements HomeworkMapper {

    private UserMapper userMapper;

    public HomeworkMapperImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public HomeworkEntity toEntity(Homework homeWork) {
        return new HomeworkEntity(homeWork.getUuid(), userMapper.toEntity(homeWork.getAuthor()), homeWork.getDateOfCompletion(), homeWork.getTask(), homeWork.getFilename());
    }

    @Override
    public Homework toDomain(HomeworkEntity homeWorkEntity) {
        return Homework.rehydrate(homeWorkEntity.getUuid(), userMapper.toDomain(homeWorkEntity.getAuthor()), homeWorkEntity.getDateOfCompletion(), homeWorkEntity.getTask(), homeWorkEntity.getFilename());
    }
}
