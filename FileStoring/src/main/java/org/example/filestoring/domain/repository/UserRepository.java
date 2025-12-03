package org.example.filestoring.domain.repository;

import org.example.filestoring.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    void saveUser(User user);

    Optional<User> getUserById(UUID uuid);

    Optional<User> getUserByName(String firstname, String lastname);
}
