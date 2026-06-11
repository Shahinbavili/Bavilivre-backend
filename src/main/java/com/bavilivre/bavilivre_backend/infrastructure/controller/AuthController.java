package com.bavilivre.bavilivre_backend.infrastructure.controller;

import com.bavilivre.bavilivre_backend.application.usecase.auth.RegisterUser;
import com.bavilivre.bavilivre_backend.application.usecase.auth.UserRegistered;
import com.bavilivre.bavilivre_backend.infrastructure.controller.request.RegisterRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RegisterUser registerUser;

    public AuthController(RegisterUser registerUser) {
        this.registerUser = registerUser;
    }

    @PostMapping("/register")
    public UserRegistered register(
            @RequestBody RegisterRequest request
    ) {
        return registerUser.register(request);
    }
}