package com.bavilivre.bavilivre_backend.domain.model.user;

import com.bavilivre.bavilivre_backend.domain.model.book.Book;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class User {
    private final UserId userId;

    private final Set<Book> borrowedBooks;
    private final Set<Book> lentBooks;

    public User(UserId userId) {
        this.userId = userId;
        this.borrowedBooks = new HashSet<>();
        this.lentBooks = new HashSet<>();
    }

    public UserId id() {
        return userId;
    }

    public boolean hasBorrowed(Book book) {
        return borrowedBooks.contains(book);
    }

    public void borrow(Book book) {
        borrowedBooks.add(book);
    }

    public void lend(Book book) {
        lentBooks.add(book);
    }

    public Set<Book> borrowedBooks() {
        return Collections.unmodifiableSet(borrowedBooks);
    }

    public Set<Book> lentBooks() {
        return Collections.unmodifiableSet(lentBooks);
    }


}
