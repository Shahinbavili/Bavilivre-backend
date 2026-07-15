package com.bavilivre.bavilivre_backend.infrastructure.persistence;

import com.bavilivre.bavilivre_backend.application.query.BookFilter;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.entity.BookJpaEntity;
import org.springframework.data.jpa.domain.Specification;

public final class BookSpecifications {

    private BookSpecifications() {
    }

    public static Specification<BookJpaEntity> withFilter(BookFilter filter) {
        return Specification.allOf(
                notArchived(),
                hasLanguage(filter.language()),
                hasCategory(filter.category()),
                hasAvailability(filter.available())
        );
    }

    private static Specification<BookJpaEntity> notArchived() {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.isFalse(root.get("archived")));
    }

    private static Specification<BookJpaEntity> hasLanguage(String language) {
        return ((root, query, criteriaBuilder) -> {
            if (language == null || language.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(
                    criteriaBuilder.lower(root.get("language")),
                    language.trim().toLowerCase()
            );
        });
    }

    private static Specification<BookJpaEntity> hasCategory(String category) {
        return ((root, query, criteriaBuilder) -> {
            if (category == null || category.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(
                    criteriaBuilder.lower(root.get("category")),
                    category.trim().toLowerCase()
            );
        });
    }

    private static Specification<BookJpaEntity> hasAvailability(Boolean available) {
        return ((root, query, criteriaBuilder) -> {
            if (available == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(
                    root.get("available"),
                    available
            );
        });
    }
}