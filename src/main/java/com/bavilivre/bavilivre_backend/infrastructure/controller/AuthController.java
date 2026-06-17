package com.bavilivre.bavilivre_backend.infrastructure.controller;

import com.bavilivre.bavilivre_backend.application.usecase.auth.LoginUser;
import com.bavilivre.bavilivre_backend.application.usecase.auth.RegisterUser;
import com.bavilivre.bavilivre_backend.application.usecase.auth.UserRegistered;
import com.bavilivre.bavilivre_backend.infrastructure.controller.request.LoginRequest;
import com.bavilivre.bavilivre_backend.infrastructure.controller.request.RegisterRequest;
import com.bavilivre.bavilivre_backend.infrastructure.controller.response.AuthResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RegisterUser registerUser;
    private final LoginUser loginUser;

    public AuthController(RegisterUser registerUser, LoginUser loginUser) {
        this.registerUser = registerUser;
        this.loginUser = loginUser;
    }

    @PostMapping("/register")
    public UserRegistered register(
            @RequestBody RegisterRequest request
    ) {
        return registerUser.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest request
    ) {
        return loginUser.login(request);
    }
}