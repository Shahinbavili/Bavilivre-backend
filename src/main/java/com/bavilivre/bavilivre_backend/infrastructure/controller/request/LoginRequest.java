package com.bavilivre.bavilivre_backend.infrastructure.controller.request;

public record LoginRequest(
        String email,
        String password
) {
}