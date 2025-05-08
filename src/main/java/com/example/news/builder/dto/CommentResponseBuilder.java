package com.example.news.builder.dto;

import com.example.news.DTO.response.comments.CommentNewsResponse;
import com.example.news.DTO.response.comments.CommentResponse;
import com.example.news.DTO.response.comments.CommentUserResponse;

import java.time.LocalDateTime;

public class CommentResponseBuilder {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private CommentUserResponse user;
    private CommentNewsResponse news;

    public CommentResponseBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public CommentResponseBuilder content(String content) {
        this.content = content;
        return this;
    }

    public CommentResponseBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public CommentResponseBuilder updatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public CommentResponseBuilder user(CommentUserResponse user) {
        this.user = user;
        return this;
    }

    public CommentResponseBuilder news(CommentNewsResponse news) {
        this.news = news;
        return this;
    }

    public CommentResponse build() {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setId(id);
        commentResponse.setContent(content);
        commentResponse.setCreatedAt(createdAt);
        commentResponse.setUpdatedAt(updatedAt);
        commentResponse.setUser(user);
       commentResponse.setNews(news);
        return commentResponse;
    }
}