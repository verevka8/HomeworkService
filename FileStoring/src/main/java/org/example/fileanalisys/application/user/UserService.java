package org.example.fileanalisys.application.user;

import java.util.UUID;
import org.example.fileanalisys.domain.model.*;


public interface UserService {
    User getUserById(UUID uuid);
    User getUserByName(String firstname, String lastname);
    UUID createUser(String firstname, String lastname);
}
