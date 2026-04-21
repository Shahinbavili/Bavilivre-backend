package com.bavilivre.bavilivre_backend.application.mapper;

import com.bavilivre.bavilivre_backend.application.dto.LentBooksDto;
import com.bavilivre.bavilivre_backend.domain.model.user.User;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class LendDtoMapper {

    public LentBooksDto toDto(User lender) {
        Map<Integer, Integer> lentBookList = lender.lentBooks().stream()
                .collect(Collectors.toMap(
                        book -> book.id().value(),
                        book -> book.ownerId().value()
                ));
        return new LentBooksDto(lentBookList);
    }

}
