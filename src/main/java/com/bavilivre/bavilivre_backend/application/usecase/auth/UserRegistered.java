package com.bavilivre.bavilivre_backend.application.usecase.auth;

public record UserRegistered(
        Integer id,
        String displayName,
        String email
) {
}