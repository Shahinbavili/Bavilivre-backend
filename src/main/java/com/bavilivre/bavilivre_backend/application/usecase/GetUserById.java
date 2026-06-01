package com.bavilivre.bavilivre_backend.application.usecase;

import com.bavilivre.bavilivre_backend.application.port.UserRepository;
import com.bavilivre.bavilivre_backend.domain.exception.UserNotFoundException;
import com.bavilivre.bavilivre_backend.domain.model.user.User;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import org.springframework.stereotype.Service;

@Service
public class GetUserById {

    private UserRepository userRepository;

    public GetUserById(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User handle(UserId userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}