package com.bavilivre.bavilivre_backend.infrastructure.controller.exception;

public class AuthenticationException extends RuntimeException {

    private final AuthenticationErrorCode code;

    public AuthenticationException(AuthenticationErrorCode code) {
        super(code.name());
        this.code = code;
    }

    public AuthenticationErrorCode code() {
        return code;
    }
}