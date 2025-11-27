package org.example.filestoring.infrastructure.persistence.mapper;


import org.example.filestoring.domain.model.User;
import org.example.filestoring.infrastructure.persistence.jpa.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserEntity toEntity(User user) {
        return new UserEntity(user.getUuid(), user.getFirstname(), user.getLastname());
    }

    @Override
    public User toDomain(UserEntity userEntity) {
        return User.rehydrate(userEntity.getUuid(), userEntity.getFirstname(), userEntity.getLastname());
    }
}
