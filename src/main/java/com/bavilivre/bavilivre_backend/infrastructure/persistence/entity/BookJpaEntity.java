package com.bavilivre.bavilivre_backend.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class BookJpaEntity {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private UserJpaEntity owner;

    protected BookJpaEntity() {
    }

    public BookJpaEntity(Integer id, UserJpaEntity owner) {
        this.id = id;
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public UserJpaEntity getOwner() {
        return owner;
    }
}