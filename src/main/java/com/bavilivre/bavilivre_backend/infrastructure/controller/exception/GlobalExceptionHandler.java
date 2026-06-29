package com.bavilivre.bavilivre_backend.infrastructure.controller.exception;

import com.bavilivre.bavilivre_backend.domain.exception.*;
import com.bavilivre.bavilivre_backend.infrastructure.controller.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookAlreadyReturnedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleBookAlreadyReturned(
            BookAlreadyReturnedException ignored
    ) {
        return new ErrorResponse(
                ("BOOK_ALREADY_RETURNED")
        );

    }

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleBookNotFound(
            BookNotFoundException ignored
    ) {
        return new ErrorResponse(
                "BOOK_NOT_FOUND"
        );
    }

    @ExceptionHandler(BorrowingNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleBorrowingNotFound(
            BorrowingNotFoundException ignored
    ) {
        return new ErrorResponse(
                "BORROWING_NOT_FOUND"
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFound(
            UserNotFoundException ignored
    ) {
        return new ErrorResponse(
                "USER_NOT_FOUND"
        );
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleAuthenticationException(
            AuthenticationException exception
    ) {
        return new ErrorResponse(
                exception.code().name()
        );
    }

    @ExceptionHandler(BookNotAvailableException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleBookNotAvailable(
            BookNotAvailableException ignored
    ) {
        return new ErrorResponse(
                "BOOK_NOT_AVAILABLE"
        );
    }
}
