package NewsApp.builder.entity;

import  NewsApp.entity.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NewsBuilder {
    private Long id;
    private String title;
    private String content;
    private String image;
    private boolean isPublished;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private User author;
    private List<Comment> comments = new ArrayList<>();
    private List<NewsCategory> newsCategories = new ArrayList<>();

    public NewsBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public NewsBuilder title(String title) {
        this.title = title;
        return this;
    }

    public NewsBuilder content(String content) {
        this.content = content;
        return this;
    }

    public NewsBuilder image(String image) {
        this.image = image;
        return this;
    }

    public NewsBuilder isPublished(boolean isPublished) {
        this.isPublished = isPublished;
        return this;
    }

    public NewsBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public NewsBuilder updatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public NewsBuilder author(User author) {
        this.author = author;
        return this;
    }

    public NewsBuilder comments(List<Comment> comments) {
        this.comments = comments != null ? comments : new ArrayList<>();
        return this;
    }

    public NewsBuilder newsCategories(List<NewsCategory> newsCategories) {
        this.newsCategories = newsCategories != null ? newsCategories : new ArrayList<>();
        return this;
    }

    public NewsBuilder addComment(Comment comment) {
        this.comments.add(comment);
        return this;
    }

    public NewsBuilder addNewsCategory(NewsCategory newsCategory) {
        this.newsCategories.add(newsCategory);
        return this;
    }

    public News build() {
        News news = new News(); // Используем пустой конструктор
        news.setId(id);
        news.setTitle(title);
        news.setContent(content);
        news.setImage(image);
        news.setPublished(isPublished);
        news.setCreatedAt(createdAt);
        news.setUpdatedAt(updatedAt);
        news.setAuthor(author);
        news.setComments(comments != null ? comments : new ArrayList<>());
        news.setNewsCategories(newsCategories != null ? newsCategories : new ArrayList<>());
        return news;
    }
}