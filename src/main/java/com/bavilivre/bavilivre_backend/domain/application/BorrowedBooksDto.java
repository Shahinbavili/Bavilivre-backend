package com.bavilivre.bavilivre_backend.domain.application;

import java.util.Map;


public record BorrowedBooksDto(Map<Integer, Integer> borrowedBookList) {
}