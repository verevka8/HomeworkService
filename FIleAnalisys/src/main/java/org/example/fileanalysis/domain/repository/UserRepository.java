package org.example.fileanalysis.domain.repository;

import org.example.fileanalysis.domain.model.User;

import java.util.UUID;

public interface UserRepository {
    void saveUser(User user);

    User getUserById(UUID uuid);

    User getUserByName(String firstname, String lastname);
}
