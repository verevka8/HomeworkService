package org.example.fileanalysis.infrastructure.persistence.mapper;

import org.example.fileanalysis.domain.model.User;
import org.example.fileanalysis.domain.model.Plagiarism;
import org.example.fileanalysis.infrastructure.persistence.jpa.entity.PlagiarismEntity;
import org.example.fileanalysis.infrastructure.persistence.jpa.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class PlagiarismMapper implements Mapper<Plagiarism, PlagiarismEntity> {

    private final Mapper<User, UserEntity> mapper;

    public PlagiarismMapper(Mapper<User, UserEntity> mapper){
        this.mapper = mapper;
    }

    @Override
    public PlagiarismEntity toEntity(Plagiarism plagiarism) {
        return new PlagiarismEntity(null, mapper.toEntity(plagiarism.getUser1()), mapper.toEntity(plagiarism.getUser2()), plagiarism.getTask());
    }

    @Override
    public Plagiarism toDomain(PlagiarismEntity plagiarismEntity) {
        return new Plagiarism(mapper.toDomain(plagiarismEntity.getUser1()), mapper.toDomain(plagiarismEntity.getUser2()), plagiarismEntity.getTask());
    }
}
