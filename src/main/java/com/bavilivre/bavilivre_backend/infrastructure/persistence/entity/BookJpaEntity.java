package com.bavilivre.bavilivre_backend.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "books")
public class BookJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private UserJpaEntity owner;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(length = 1000)
    private String description;

    private String language;

    private String category;

    @Column(nullable = false)
    private boolean available;

    @Column(nullable = false)
    private boolean archived;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    protected BookJpaEntity() {
    }

    public BookJpaEntity(
            Integer id,
            UserJpaEntity owner,
            String title,
            String author,
            String description,
            String language,
            String category,
            boolean available,
            boolean archived,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.owner = owner;
        this.title = title;
        this.author = author;
        this.description = description;
        this.language = language;
        this.category = category;
        this.available = available;
        this.archived = archived;
        this.createdAt = createdAt;
    }

    public BookJpaEntity(
            Integer id,
            UserJpaEntity owner,
            String title,
            String author,
            String description,
            String language,
            String category,
            boolean available,
            boolean archived
    ) {
        this(
                id,
                owner,
                title,
                author,
                description,
                language,
                category,
                available,
                archived,
                null
        );
    }

    public Integer getId() {
        return id;
    }

    public UserJpaEntity getOwner() {
        return owner;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean isArchived() {
        return archived;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}