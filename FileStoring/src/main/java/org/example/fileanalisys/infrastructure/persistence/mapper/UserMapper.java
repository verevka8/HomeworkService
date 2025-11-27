package org.example.fileanalisys.infrastructure.persistence.mapper;


import org.example.fileanalisys.domain.model.User;
import org.example.fileanalisys.infrastructure.persistence.jpa.entity.UserEntity;

public interface UserMapper {
    UserEntity toEntity(User user);
    User toDomain(UserEntity userEntity);
}
