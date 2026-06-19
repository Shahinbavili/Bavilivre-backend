package com.bavilivre.bavilivre_backend.infrastructure.controller.exception;

public enum AuthenticationErrorCode {
    EMAIL_ALREADY_REGISTERED,
    INVALID_CREDENTIALS,
    USER_NOT_FOUND,
    CURRENT_USER_NOT_FOUND,
}