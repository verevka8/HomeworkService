package org.example.fileanalysis.infrastructure.persistence.mapper;


import org.example.fileanalysis.domain.model.User;
import org.example.fileanalysis.infrastructure.persistence.jpa.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserEntity> {
    @Override
    public UserEntity toEntity(User user) {
        return new UserEntity(user.getUuid(), user.getFirstname(), user.getLastname());
    }

    @Override
    public User toDomain(UserEntity userEntity) {
        return User.rehydrate(userEntity.getUuid(), userEntity.getFirstname(), userEntity.getLastname());
    }
}
