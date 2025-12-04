package com.bavilivre.bavilivre_backend.application.dto;

import java.util.Map;


public record BorrowedBooksDto(Map<Integer, Integer> borrowedBookList) {
}