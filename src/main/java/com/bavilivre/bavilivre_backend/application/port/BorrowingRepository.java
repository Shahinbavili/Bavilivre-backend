package com.bavilivre.bavilivre_backend.application.port;

import com.bavilivre.bavilivre_backend.domain.model.borrowing.Borrowing;
import com.bavilivre.bavilivre_backend.domain.model.borrowing.BorrowingId;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;

import java.util.List;
import java.util.Optional;

public interface BorrowingRepository {

    Optional<Borrowing> findById(BorrowingId borrowingId);

    List<Borrowing> findByBorrower_Id(UserId borrowerId);

    List<Borrowing> findByLender_Id(UserId lenderId);

    Borrowing save(Borrowing borrowing);
}