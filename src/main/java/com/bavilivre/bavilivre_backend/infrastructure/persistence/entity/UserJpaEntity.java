package com.bavilivre.bavilivre_backend.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserJpaEntity {

    @Id
    private Integer id;

    protected UserJpaEntity() {
    }

    public UserJpaEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}