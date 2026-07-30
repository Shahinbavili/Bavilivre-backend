package com.bavilivre.bavilivre_backend.application.query;

public record BookFilter(
        String title,
        String language,
        String category,
        Boolean available,
        String sort,
        Integer page,
        Integer size
) {
}