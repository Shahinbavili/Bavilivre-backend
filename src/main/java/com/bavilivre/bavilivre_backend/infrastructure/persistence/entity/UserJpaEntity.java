package com.bavilivre.bavilivre_backend.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserJpaEntity {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String displayName;

    protected UserJpaEntity() {
    }

    public UserJpaEntity(Integer id, String displayName) {

        this.id = id;
        this.displayName = displayName;
    }

    public Integer getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }
}