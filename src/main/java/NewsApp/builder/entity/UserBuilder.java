package NewsApp.builder.entity;
import NewsApp.entity.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserBuilder {
    private Long id;
    private String username;
    private String email;
    private String password;
    private User.Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<News> news = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();

    public UserBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder role(User.Role role) {
        this.role = role;
        return this;
    }

    public UserBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public UserBuilder updatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public UserBuilder news(List<News> news) {
        this.news = news;
        return this;
    }

    public UserBuilder comments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public UserBuilder addNews(News newsItem) {
        this.news.add(newsItem);
        return this;
    }

    public UserBuilder addComment(Comment comment) {
        this.comments.add(comment);
        return this;
    }

    public User build() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        user.setCreatedAt(createdAt);
        user.setUpdatedAt(updatedAt);
        user.setNews(news);
        user.setComments(comments);
        return user;
    }
}