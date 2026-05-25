package com.bavilivre.bavilivre_backend.infrastructure.controller.response;

public record ErrorResponse(

        String message,
        String exceptionMessage
) {
}