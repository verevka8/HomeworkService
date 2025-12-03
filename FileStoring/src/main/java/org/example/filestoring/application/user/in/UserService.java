package org.example.filestoring.application.user.in;

import java.util.UUID;
import org.example.filestoring.domain.model.*;


public interface UserService {
    User getUserById(UUID uuid);
    User getUserByName(String firstname, String lastname);
    UUID createUser(String firstname, String lastname);
}
