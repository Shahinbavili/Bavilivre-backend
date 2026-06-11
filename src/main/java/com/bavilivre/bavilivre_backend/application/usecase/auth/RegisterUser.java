package com.bavilivre.bavilivre_backend.application.usecase.auth;

import com.bavilivre.bavilivre_backend.application.port.PasswordHasher;
import com.bavilivre.bavilivre_backend.application.port.UserAccountRepository;
import com.bavilivre.bavilivre_backend.application.port.UserRepository;
import com.bavilivre.bavilivre_backend.domain.model.user.User;
import com.bavilivre.bavilivre_backend.domain.model.useraccount.UserAccount;
import com.bavilivre.bavilivre_backend.infrastructure.controller.request.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public class RegisterUser {

    private final UserRepository userRepository;
    private final UserAccountRepository userAccountRepository;
    private final PasswordHasher passwordHasher;

    public RegisterUser(UserRepository userRepository, UserAccountRepository userAccountRepository, PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.userAccountRepository = userAccountRepository;
        this.passwordHasher = passwordHasher;
    }

    public UserRegistered register(RegisterRequest request) {
        if (userAccountRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email already used");
        }

        User savedUser = userRepository.create(request.displayName());

        String passwordHash = passwordHasher.hash(request.password());

        userAccountRepository.save(
                new UserAccount(
                        savedUser.id(),
                        request.email(),
                        passwordHash
                )
        );
        return new UserRegistered(
                savedUser.id().value(),
                savedUser.displayName(),
                request.email()
        );
    }

}