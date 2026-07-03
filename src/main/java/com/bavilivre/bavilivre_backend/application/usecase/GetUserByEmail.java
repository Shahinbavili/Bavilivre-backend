package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.application.port.UserAccountRepository;
import com.bavilivre.bavilivre_backend.domain.exception.UserNotFoundException;
import com.bavilivre.bavilivre_backend.domain.model.useraccount.UserAccount;
import org.springframework.stereotype.Service;

@Service
public class GetUserByEmail {

    private final UserAccountRepository userAccountRepository;

    public GetUserByEmail(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public UserAccount handle(String email) {
        return userAccountRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email)
                );
    }
}