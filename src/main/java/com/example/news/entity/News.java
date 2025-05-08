package com.example.news.entity;

import com.example.news.builder.entity.NewsBuilder;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(length = 255)
    private String image;

    @Column(name = "is_published", nullable = false)
    private boolean isPublished;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
//    @JsonIgnoreProperties("news")
    private User author;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NewsCategory> newsCategories;

    // Статический метод для создания builder
    public static NewsBuilder builder() {
        return new NewsBuilder();
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public boolean isPublished() { return isPublished; }
    public void setPublished(boolean published) { isPublished = published; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public User getAuthor() { return author; }
    public void setAuthor(User author) { this.author = author; }

    public List<Comment> getComments() {
        if (comments == null) comments = new ArrayList<>();
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments != null ? comments : new ArrayList<>();
    }

    public List<NewsCategory> getNewsCategories() {
        if (newsCategories == null) newsCategories = new ArrayList<>();
        return newsCategories;
    }

    public void setNewsCategories(List<NewsCategory> newsCategories) {
        this.newsCategories = newsCategories != null ? newsCategories : new ArrayList<>();
    }

    // Методы для управления связями
    public void addComment(Comment comment) {
        getComments().add(comment);
        comment.setNews(this);
    }
    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setNews(null); // важно для JPA
    }

    public void addCategory(Category category) {
        NewsCategory newsCategory = NewsCategory.builder()
                .news(this)
                .category(category)
                .build();
        getNewsCategories().add(newsCategory);
    }

}