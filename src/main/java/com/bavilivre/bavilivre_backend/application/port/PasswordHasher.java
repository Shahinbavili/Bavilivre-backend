package com.bavilivre.bavilivre_backend.application.port;

public interface PasswordHasher {

    String hash(String password);

    boolean matches(String rawPassword, String hash);
}