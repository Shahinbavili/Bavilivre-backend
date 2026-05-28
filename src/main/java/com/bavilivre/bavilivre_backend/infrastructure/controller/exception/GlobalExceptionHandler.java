package com.bavilivre.bavilivre_backend.infrastructure.controller.exception;

import com.bavilivre.bavilivre_backend.domain.exception.BookAlreadyReturnedException;
import com.bavilivre.bavilivre_backend.domain.exception.BookNotFoundException;
import com.bavilivre.bavilivre_backend.domain.exception.BorrowingNotFoundException;
import com.bavilivre.bavilivre_backend.domain.exception.UserNotFoundException;
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
            BookAlreadyReturnedException exception
    ) {
        return new ErrorResponse(
                ("BOOK_ALREADY_RETURNED"),
                exception.getMessage()
        );

    }

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleBookNotFound(
            BookNotFoundException exception
    ) {
        return new ErrorResponse(
                "BOOK_NOT_FOUND",
                exception.getMessage()
        );
    }

    @ExceptionHandler(BorrowingNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleBorrowingNotFound(
            BorrowingNotFoundException exception
    ) {
        return new ErrorResponse(
                "BORROWING_NOT_FOUND",
                exception.getMessage()
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFound(
            UserNotFoundException exception
    ) {
        return new ErrorResponse(
                "USER_NOT_FOUND",
                exception.getMessage()
        );
    }
}
