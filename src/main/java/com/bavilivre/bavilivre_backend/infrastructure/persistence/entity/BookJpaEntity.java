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

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(length = 1000)
    private String description;

    private String language;

    private String category;

    protected BookJpaEntity() {
    }

    public BookJpaEntity(
            Integer id,
            UserJpaEntity owner,
            String title,
            String author,
            String description,
            String language,
            String category
    ) {
        this.id = id;
        this.owner = owner;
        this.title = title;
        this.author = author;
        this.description = description;
        this.language = language;
        this.category = category;
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
}