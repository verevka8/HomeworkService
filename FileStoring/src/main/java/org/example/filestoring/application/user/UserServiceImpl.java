package org.example.filestoring.application.user;

import org.example.filestoring.domain.model.User;
import org.example.filestoring.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public User getUserById(UUID uuid) {
        return repository.getUserById(uuid);
    }

    @Override
    public User getUserByName(String firstname, String lastname) {
        return repository.getUserByName(firstname, lastname);
    }

    @Override
    public UUID createUser(String firstname, String lastname) {
        User newUser = User.createNew(firstname, lastname);
        repository.saveUser(newUser);
        return newUser.getUuid();
    }
}
