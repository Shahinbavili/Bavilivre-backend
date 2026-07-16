package com.bavilivre.bavilivre_backend.infrastructure.persistence.adapter;

import com.bavilivre.bavilivre_backend.application.port.BookRepository;
import com.bavilivre.bavilivre_backend.application.query.BookFilter;
import com.bavilivre.bavilivre_backend.application.query.PageResult;
import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.BookSpecifications;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.entity.BookJpaEntity;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.mapper.BookJpaMapper;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.repository.BookSpringDataRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookJpaAdapter implements BookRepository {

    private final BookSpringDataRepository repository;
    private final BookJpaMapper mapper = new BookJpaMapper();

    public BookJpaAdapter(BookSpringDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Book> findById(BookId id) {
        return repository.findById(id.value())
                .map(mapper::toDomain);
    }

    @Override
    public PageResult<Book> findByFilter(BookFilter filter) {

        Sort sort = switch (filter.sort()) {
            case "title" -> Sort.by("title");
            case "createdAt" -> Sort.by(Sort.Direction.DESC, "createdAt");
            default -> Sort.unsorted();
        };

        Pageable pageable = PageRequest.of(
                filter.page(),
                filter.size(),
                sort
        );

        Specification<BookJpaEntity> specification =
                BookSpecifications.withFilter(filter);

        Page<BookJpaEntity> entityPage =
                repository.findAll(specification, pageable);

        List<Book> books = entityPage.getContent()
                .stream()
                .map(mapper::toDomain)
                .toList();

        return new PageResult<>(
                books,
                entityPage.getTotalElements(),
                entityPage.getTotalPages(),
                entityPage.getNumber(),
                entityPage.getSize()
        );
    }

    @Override
    public List<Book> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Book> search(String query) {
        String normalizedQuery = query == null ? "" : query.trim().toLowerCase();

        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .filter(book -> !book.archived())
                .filter(book -> containsIgnoreCase(book.title(), normalizedQuery) || containsIgnoreCase(book.author(), normalizedQuery))
                .toList();
    }

    private boolean containsIgnoreCase(String value, String query) {
        return value != null && value.toLowerCase().contains(query);
    }

    @Override
    public Book save(Book book) {

        var entity = mapper.toEntity(book);

        var savedEntity = repository.save(entity);

        return mapper.toDomain(savedEntity);
    }
}