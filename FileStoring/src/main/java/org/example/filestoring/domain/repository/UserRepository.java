package org.example.filestoring.domain.repository;

import org.example.filestoring.domain.model.User;

import java.util.UUID;

public interface UserRepository {
    void saveUser(User user);

    User getUserById(UUID uuid);

    User getUserByName(String firstname, String lastname);
}
