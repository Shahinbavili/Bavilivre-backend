package com.bavilivre.bavilivre_backend.infrastructure.controller;

import com.bavilivre.bavilivre_backend.application.usecase.auth.GetCurrentAuthenticatedUser;
import com.bavilivre.bavilivre_backend.application.usecase.auth.LoginUser;
import com.bavilivre.bavilivre_backend.application.usecase.auth.RegisterUser;
import com.bavilivre.bavilivre_backend.application.usecase.auth.UserRegistered;
import com.bavilivre.bavilivre_backend.infrastructure.controller.request.LoginRequest;
import com.bavilivre.bavilivre_backend.infrastructure.controller.request.RegisterRequest;
import com.bavilivre.bavilivre_backend.infrastructure.controller.response.AuthResponse;
import com.bavilivre.bavilivre_backend.infrastructure.controller.response.CurrentUserResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegisterUser registerUser;
    private final LoginUser loginUser;
    private final GetCurrentAuthenticatedUser getCurrentAuthenticatedUser;

    public AuthController(RegisterUser registerUser, LoginUser loginUser, GetCurrentAuthenticatedUser getCurrentAuthenticatedUser) {
        this.registerUser = registerUser;
        this.loginUser = loginUser;
        this.getCurrentAuthenticatedUser = getCurrentAuthenticatedUser;
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

    @GetMapping("/me")
    public CurrentUserResponse me(Authentication authentication) {

        return getCurrentAuthenticatedUser.get(
                authentication.getName()
        );
    }
}