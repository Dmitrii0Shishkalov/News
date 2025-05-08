package com.example.news.entity;

import com.example.news.builder.entity.NewsCategoryBuilder;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "news_categories")
public class NewsCategory {

    public static NewsCategoryBuilder builder() {
        return new NewsCategoryBuilder();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id", nullable = false)
    private News news;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // Дополнительные поля для связи (если нужно)
    @Column(name = "assigned_at")
    private LocalDateTime assignedAt = LocalDateTime.now();


    public Long getId() {
        return id;
    }

    public News getNews() {
        return news;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setAssignedAt(LocalDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }

}