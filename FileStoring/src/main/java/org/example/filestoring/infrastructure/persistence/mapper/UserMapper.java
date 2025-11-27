package org.example.filestoring.infrastructure.persistence.mapper;


import org.example.filestoring.domain.model.User;
import org.example.filestoring.infrastructure.persistence.jpa.entity.UserEntity;

public interface UserMapper {
    UserEntity toEntity(User user);
    User toDomain(UserEntity userEntity);
}
