package com.example.news.DTO.response.news;


import com.example.news.builder.dto.NewsAuthorBuilder;


public class NewsAuthorDto {
    private Long id;
    private String username;
    private String email;

    public   static NewsAuthorBuilder builder(){
        return new NewsAuthorBuilder();
    };
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
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

}