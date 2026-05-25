package com.bavilivre.bavilivre_backend.infrastructure.controller.response;

import java.util.Map;

public record BorrowedBooksDto(Map<Integer, Integer> borrowedBookList) {
}