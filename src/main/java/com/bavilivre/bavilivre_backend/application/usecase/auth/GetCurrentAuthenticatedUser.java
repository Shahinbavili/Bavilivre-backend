package com.bavilivre.bavilivre_backend.application.usecase.auth;

import com.bavilivre.bavilivre_backend.application.port.UserAccountRepository;
import com.bavilivre.bavilivre_backend.application.port.UserRepository;
import com.bavilivre.bavilivre_backend.domain.model.user.User;
import com.bavilivre.bavilivre_backend.domain.model.useraccount.UserAccount;
import com.bavilivre.bavilivre_backend.infrastructure.controller.response.CurrentUserResponse;
import org.springframework.stereotype.Service;

@Service
public class GetCurrentAuthenticatedUser {

    private final UserRepository userRepository;
    private final UserAccountRepository userAccountRepository;

    public GetCurrentAuthenticatedUser(UserRepository userRepository, UserAccountRepository userAccountRepository) {
        this.userRepository = userRepository;
        this.userAccountRepository = userAccountRepository;
    }

    public CurrentUserResponse get(String email) {
        UserAccount userAccount = userAccountRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("User account not found"));

        User user = userRepository
                .findById(userAccount.userId())
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found"));

        return new CurrentUserResponse(
                user.id().value(),
                user.displayName(),
                userAccount.email()
        );
    }
}