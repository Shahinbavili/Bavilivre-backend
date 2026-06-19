package com.bavilivre.bavilivre_backend.infrastructure.controller;

import com.bavilivre.bavilivre_backend.application.usecase.GetUserById;
import com.bavilivre.bavilivre_backend.domain.model.user.User;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import com.bavilivre.bavilivre_backend.infrastructure.controller.response.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final GetUserById getUserById;

    public UserController(GetUserById getUserById) {
        this.getUserById = getUserById;
    }

    @GetMapping("/{id}")
    public UserDto getUser(
            @PathVariable Integer id
    ) {
        User user = getUserById.handle(
                new UserId(id)
        );
        return new UserDto(
                user.id().value(),
                user.displayName()
        );
    }
}