package org.example.filestoring.infrastructure.web.user;

import org.example.filestoring.application.user.UserService;
import org.example.filestoring.domain.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users/{uuid}")
    public User getUserById(@PathVariable("uuid") UUID uuid) {
        return service.getUserById(uuid);
    }

    @GetMapping("/users")
    public User getUserByName(@RequestParam String firstname, @RequestParam String lastname) {
        return service.getUserByName(firstname, lastname);
    }

    @PostMapping("/users")
    public UUID createUser(@RequestBody CreateUserRequest request){
        return service.createUser(request.firstname(), request.lastname());
    }
}
