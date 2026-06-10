package com.bavilivre.bavilivre_backend.infrastructure.controller.response;

public record CurrentUserResponse(
        Integer id,
        String displayName,
        String email
) {
}