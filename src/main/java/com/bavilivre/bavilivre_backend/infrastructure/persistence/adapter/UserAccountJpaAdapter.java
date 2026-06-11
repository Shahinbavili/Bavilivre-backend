package com.bavilivre.bavilivre_backend.infrastructure.persistence.adapter;

import com.bavilivre.bavilivre_backend.application.port.UserAccountRepository;
import com.bavilivre.bavilivre_backend.domain.model.useraccount.UserAccount;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.mapper.UserAccountJpaMapper;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.repository.UserAccountSpringDataRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserAccountJpaAdapter implements UserAccountRepository {

    private final UserAccountSpringDataRepository repository;
    private final UserAccountJpaMapper mapper;

    public UserAccountJpaAdapter(UserAccountSpringDataRepository repository, UserAccountJpaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<UserAccount> findByEmail(String email) {
        return repository.findByEmail(email)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public UserAccount save(UserAccount userAccount) {
        var entity = mapper.toEntity(userAccount);
        var savedEntity = repository.save(entity);

        return mapper.toDomain(savedEntity);
    }
}