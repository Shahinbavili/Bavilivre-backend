package com.bavilivre.bavilivre_backend.infrastructure.persistence.mapper;

import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.Borrowing;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.BorrowingId;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.entity.BookJpaEntity;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.entity.BorrowingJpaEntity;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.entity.UserJpaEntity;

public class BorrowingJpaMapper {

    private final BookJpaMapper bookMapper = new BookJpaMapper();
    private final UserJpaMapper userMapper = new UserJpaMapper();

    public Borrowing toDomain(BorrowingJpaEntity entity) {
        Borrowing borrowing = new Borrowing(
                new BorrowingId(entity.getId()),
                new BookId(entity.getBook().getId()),
                new UserId(entity.getBorrower().getId()),
                new UserId(entity.getLender().getId()),
                entity.getBorrowedAt()
        );

        if (entity.getReturnedAt() != null) {

            borrowing.returnBook(entity.getReturnedAt());

        }

        return borrowing;
    }


    public BorrowingJpaEntity toEntity(Borrowing borrowing) {
        return new BorrowingJpaEntity(
                borrowing.id().value(),
                new BookJpaEntity(
                        borrowing.bookId().value(),
                        new UserJpaEntity(borrowing.lenderId().value())
                ),
                new UserJpaEntity(borrowing.borrowerId().value()),
                new UserJpaEntity(borrowing.lenderId().value()),
                borrowing.borrowedAt(),
                borrowing.returnedAt()
        );
    }
}