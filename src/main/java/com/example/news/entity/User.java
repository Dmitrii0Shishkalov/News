package com.example.news.entity;

import com.example.news.builder.entity.UserBuilder;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "users")
public class User {
    public static UserBuilder builder() {
        return new UserBuilder();
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /* Связь 1-to-M с news */
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnoreProperties("news")
    private List<News> news = new ArrayList<>();

    /* Связь 1-to-M с Comments */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();


    public enum Role {
        ADMIN, AUTHOR, GUEST
    }
    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<News> getNews() {
        return news;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setUser(this);
    }

    // Метод для удобного добавления новости
    public void addNews(News newsItem) {
        news.add(newsItem);
        newsItem.setAuthor(this);
    }

    // Метод для удобного удаления новости
    public void removeNews(News newsItem) {
        news.remove(newsItem);
        newsItem.setAuthor(null);
    }
}