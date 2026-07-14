package com.bavilivre.bavilivre_backend.infrastructure.persistence.adapter;

import com.bavilivre.bavilivre_backend.application.port.BookRepository;
import com.bavilivre.bavilivre_backend.application.query.BookFilter;
import com.bavilivre.bavilivre_backend.domain.model.book.Book;
import com.bavilivre.bavilivre_backend.domain.model.book.BookId;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.mapper.BookJpaMapper;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.repository.BookSpringDataRepository;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
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
    public List<Book> findByFilter(BookFilter filter) {
        var books = repository.findAll().stream()
                .map(mapper::toDomain)
                .filter(book -> !book.archived())
                .filter(book -> filter.language() == null || book.language().equalsIgnoreCase(filter.language())
                )
                .filter(book -> filter.category() == null || book.category().equalsIgnoreCase(filter.category())
                )
                .filter(book -> filter.available() == null || book.available() == filter.available()
                );

        if ("title".equalsIgnoreCase(filter.sort())) {
            books = books.sorted(Comparator.comparing(
                            Book::title,
                            String.CASE_INSENSITIVE_ORDER
                    )
            );
        }

        return books.toList();
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