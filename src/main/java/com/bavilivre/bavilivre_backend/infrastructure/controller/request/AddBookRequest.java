package com.bavilivre.bavilivre_backend.infrastructure.controller.request;

public record AddBookRequest(
        String title,
        String author,
        String description,
        String language,
        String category
) {
}