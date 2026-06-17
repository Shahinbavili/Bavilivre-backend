package com.bavilivre.bavilivre_backend.application.usecase.auth;

import com.bavilivre.bavilivre_backend.application.port.PasswordHasher;
import com.bavilivre.bavilivre_backend.application.port.UserAccountRepository;
import com.bavilivre.bavilivre_backend.domain.model.useraccount.UserAccount;
import com.bavilivre.bavilivre_backend.infrastructure.controller.request.LoginRequest;
import com.bavilivre.bavilivre_backend.infrastructure.controller.response.AuthResponse;
import com.bavilivre.bavilivre_backend.infrastructure.security.JwtService;
import org.springframework.stereotype.Service;

@Service
public class LoginUser {

    private final UserAccountRepository userAccountRepository;
    private final PasswordHasher passwordHasher;
    private final JwtService jwtService;

    public LoginUser(UserAccountRepository userAccountRepository, PasswordHasher passwordHasher, JwtService jwtService) {
        this.userAccountRepository = userAccountRepository;
        this.passwordHasher = passwordHasher;
        this.jwtService = jwtService;
    }

    public AuthResponse login(LoginRequest request) {
        UserAccount account = userAccountRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        boolean passwordMatches = passwordHasher.matches(
                request.password(),
                account.passwordHash()
        );
        if (!passwordMatches) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        String token = jwtService.generateToken(account.email());

        return new AuthResponse(token);
    }
}