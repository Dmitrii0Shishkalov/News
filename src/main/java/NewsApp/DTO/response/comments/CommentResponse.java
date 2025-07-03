package NewsApp.DTO.response.comments;


import NewsApp.builder.dto.CommentResponseBuilder;
import NewsApp.entity.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public class CommentResponse {
    @NotNull
    private Long id;

    @NotBlank
    private String content;

    @PastOrPresent
    private LocalDateTime createdAt;

    @PastOrPresent
    private LocalDateTime updatedAt;

//    @Valid
//    private CommentUserResponse user;

    @Valid
    private CommentNewsResponse news;

    public CommentResponseBuilder builder(){
        return  new CommentResponseBuilder();
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

//    public CommentUserResponse getUser() {
//        return user;
//    }

    public CommentNewsResponse getNews() {
        return news;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

//    public void setUser(CommentUserResponse user) {
//        this.user = user;
//    }

    public void setNews(CommentNewsResponse news) {
        this.news = news;
    }
}
