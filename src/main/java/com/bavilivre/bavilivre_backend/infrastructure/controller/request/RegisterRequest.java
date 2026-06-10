package com.bavilivre.bavilivre_backend.infrastructure.controller.request;

public record RegisterRequest(
        String displayName,
        String email,
        String password
) {
}