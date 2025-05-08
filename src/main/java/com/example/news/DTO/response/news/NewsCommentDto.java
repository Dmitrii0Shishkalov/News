package com.example.news.DTO.response.news;

public class NewsCommentDto {
    private Long id;
    private String content;

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
