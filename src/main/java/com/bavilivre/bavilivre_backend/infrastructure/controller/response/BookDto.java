package com.bavilivre.bavilivre_backend.infrastructure.controller.response;

import java.time.LocalDateTime;

public record BookDto(
        Integer id,
        Integer ownerId,
        String title,
        String author,
        String description,
        String language,
        String category,
        boolean available,
        boolean archived,
        LocalDateTime createdAt
) {
}