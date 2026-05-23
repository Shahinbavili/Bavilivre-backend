package com.bavilivre.bavilivre_backend.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "borrowings")
public class BorrowingJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookJpaEntity book;

    @ManyToOne
    @JoinColumn(name = "borrower_id", nullable = false)
    private UserJpaEntity borrower;

    @ManyToOne
    @JoinColumn(name = "lender_id", nullable = false)
    private UserJpaEntity lender;

    private LocalDate borrowedAt;

    private LocalDate returnedAt;

    protected BorrowingJpaEntity() {
    }

    public BorrowingJpaEntity(
            Integer id,
            BookJpaEntity book,
            UserJpaEntity borrower,
            UserJpaEntity lender,
            LocalDate borrowedAt,
            LocalDate returnedAt
    ) {
        this.id = id;
        this.book = book;
        this.borrower = borrower;
        this.lender = lender;
        this.borrowedAt = borrowedAt;
        this.returnedAt = returnedAt;
    }

    public Integer getId() {
        return id;
    }

    public BookJpaEntity getBook() {
        return book;
    }

    public UserJpaEntity getBorrower() {
        return borrower;
    }

    public UserJpaEntity getLender() {
        return lender;
    }

    public LocalDate getBorrowedAt() {
        return borrowedAt;
    }

    public LocalDate getReturnedAt() {
        return returnedAt;
    }
}