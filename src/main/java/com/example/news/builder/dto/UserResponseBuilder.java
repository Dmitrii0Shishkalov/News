package com.example.news.builder.dto;

import com.example.news.DTO.response.news.NewsResponse;
import com.example.news.DTO.response.UserResponse;

import java.util.List;

public class UserResponseBuilder {
    private Long id;
    private String username;
    private String email;
    private  String password;
    private  String role;
    private List<NewsResponse> news;


    public UserResponseBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public UserResponseBuilder username(String username) {
        this.username = username;
        return  this;
    }
    public UserResponseBuilder role(String role) {
        this.role = role;
        return  this;
    }
    public UserResponseBuilder password(String password) {
        this.password = password;
        return this;
    }
    public UserResponseBuilder news(List<NewsResponse> news) {
        this.news = news;
        return this;
    }
    public UserResponseBuilder email (String email) {
        this.email = email;
        return  this;
    }
    public UserResponse build() {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(id);
        userResponse.setUsername(username);
        userResponse.setEmail(email);
        userResponse.setPassword(password);
        userResponse.setRole(role);
        userResponse.setNews(news);

        return userResponse;
    }
}
