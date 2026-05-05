package com.bavilivre.bavilivre_backend.application.port;

import com.bavilivre.bavilivre_backend.domain.model.borrowing.Borrowing;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.BorrowingId;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;

import java.util.List;
import java.util.Optional;

public interface BorrowingRepository {

    Optional<Borrowing> findById(BorrowingId borrowingId);

    List<Borrowing> findByBorrowerId(UserId borrowerId);

    List<Borrowing> findByOwnerId(UserId ownerId);

    Borrowing save(Borrowing borrowing);
}