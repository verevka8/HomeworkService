package org.example.filestoring.infrastructure.web.api.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.filestoring.application.user.in.UserService;
import org.example.filestoring.domain.model.User;
import org.example.filestoring.infrastructure.web.api.user.dto.CreateUserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@Tag(name = "Пользователи", description = "CRUD-операции с пользователями")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users/{uuid}")
    @Operation(
            summary = "Получить пользователя по UUID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь найден"),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден")
            }
    )
    public User getUserById(@Parameter(description = "Идентификатор пользователя", required = true)
                            @PathVariable("uuid") UUID uuid) {
        return service.getUserById(uuid);
    }

    @GetMapping("/users")
    @Operation(
            summary = "Получить пользователя по имени и фамилии",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь найден"),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден")
            }
    )
    public User getUserByName(@Parameter(description = "Имя пользователя", required = true) @RequestParam String firstname,
                              @Parameter(description = "Фамилия пользователя", required = true) @RequestParam String lastname) {
        return service.getUserByName(firstname, lastname);
    }

    @PostMapping("/users")
    @Operation(
            summary = "Создать пользователя",
            responses = @ApiResponse(responseCode = "200", description = "Пользователь создан")
    )
    public UUID createUser(@RequestBody CreateUserRequest request) {
        System.out.println(1);
        return service.createUser(request.firstname(), request.lastname());
    }
}