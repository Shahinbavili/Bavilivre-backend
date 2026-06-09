package com.bavilivre.bavilivre_backend.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "user_accounts",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_user_accounts_email",
                        columnNames = "email"
                )
        }
)
public class UserAccountJpaEntity {

    @Id
    private Integer userId;

    @Column(nullable = false)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    protected UserAccountJpaEntity() {
    }

    public UserAccountJpaEntity(Integer userId, String email, String passwordHash) {
        this.userId = userId;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}