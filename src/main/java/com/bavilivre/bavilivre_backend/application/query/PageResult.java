package com.bavilivre.bavilivre_backend.application.query;

import java.util.List;

public record PageResult<T>(
        List<T> content,
        long totalElements,
        int totalPages,
        int page,
        int size
) {

}