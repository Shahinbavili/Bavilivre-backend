package com.bavilivre.bavilivre_backend.domain.exception;

import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;

public class UserIsNotBookOwnerException extends RuntimeException {

    public UserIsNotBookOwnerException(UserId userId, BookId bookId) {
        super("User " + userId.value() + " is not the owner of book " + bookId.value());
    }
}