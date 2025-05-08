package com.example.news.builder.entity;


import com.example.news.entity.*;

import java.time.LocalDateTime;

public class CommentBuilder {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private User user;
    private News news;

    public CommentBuilder() {
    }

    public CommentBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public CommentBuilder content(String content) {
        this.content = content;
        return this;
    }

    public CommentBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public CommentBuilder updatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public CommentBuilder user(User user) {
        this.user = user;
        return this;
    }

    public CommentBuilder news(News news) {
        this.news = news;
        return this;
    }

    public Comment build() {
        Comment comment = new Comment();
        comment.setId(this.id);
        comment.setContent(this.content);
        comment.setCreatedAt(this.createdAt);
        comment.setUpdatedAt(this.updatedAt);
        comment.setUser(this.user);
        comment.setNews(this.news);
        return comment;
    }
}
