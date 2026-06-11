package com.bavilivre.bavilivre_backend.infrastructure.security;

import com.bavilivre.bavilivre_backend.application.port.PasswordHasher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BcryptPasswordHasher implements PasswordHasher {

    private final PasswordEncoder passwordEncoder;

    public BcryptPasswordHasher(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String hash(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean matches(String rawPassword, String hash) {

        return passwordEncoder.matches(rawPassword, hash);
    }
}