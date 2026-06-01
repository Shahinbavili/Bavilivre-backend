package com.bavilivre.bavilivre_backend.infrastructure.persistence.adapter;

import com.bavilivre.bavilivre_backend.application.port.UserRepository;
import com.bavilivre.bavilivre_backend.domain.model.user.User;
import com.bavilivre.bavilivre_backend.domain.model.user.UserId;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.mapper.UserJpaMapper;
import com.bavilivre.bavilivre_backend.infrastructure.persistence.repository.UserSpringDataRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserJpaAdapter implements UserRepository {

    private final UserSpringDataRepository repository;
    private final UserJpaMapper mapper = new UserJpaMapper();

    public UserJpaAdapter(UserSpringDataRepository repository) {
        this.repository = repository;
    }


    @Override
    public Optional<User> findById(UserId userId) {
        return repository.findById(userId.value())
                .map(mapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public User save(User user) {

        var entity = mapper.toEntity(user);

        var savedEntity = repository.save(entity);

        return mapper.toDomain(savedEntity);
    }
}