package com.example.news.entity;

import com.example.news.builder.entity.CategoryBuilder;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")

public class Category {

    public static CategoryBuilder builder() {
        return new CategoryBuilder();
    }
    public Long getId() {
        return id;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50, unique = true)
    private String slug;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /* Обратная сторона M-to-M связи */
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<NewsCategory> newsCategories = new ArrayList<>();


    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<NewsCategory> getNewsCategories() {
        return newsCategories;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setNewsCategories(List<NewsCategory> newsCategories) {
        this.newsCategories = newsCategories;
    }
}