package com.bavilivre.bavilivre_backend.infrastructure.controller.request;

public record UpdateBookRequest(
        String title,
        String author,
        String description,
        String language,
        String category
) {
}