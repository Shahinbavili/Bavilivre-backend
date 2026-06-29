package com.bavilivre.bavilivre_backend.infrastructure.persistence.mapper;

import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.Borrowing;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.BorrowingId;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.entity.BookJpaEntity;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.entity.BorrowingJpaEntity;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.entity.UserJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class BorrowingJpaMapper {

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
        Integer entityId = borrowing.id() == null
                ? null
                : borrowing.id().value();

        return new BorrowingJpaEntity(
                entityId,
                new BookJpaEntity(
                        borrowing.bookId().value(),
                        new UserJpaEntity(
                                borrowing.lenderId().value(),
                                "Unknown"
                        ),
                        "",
                        "",
                        null,
                        null,
                        null,
                        false
                ),
                new UserJpaEntity(
                        borrowing.borrowerId().value(),
                        "Unknown"
                ),
                new UserJpaEntity(
                        borrowing.lenderId().value(),
                        "Unknown"
                ),
                borrowing.borrowedAt(),
                borrowing.returnedAt()
        );
    }
}