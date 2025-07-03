package NewsApp.DTO.response.news;
import NewsApp.DTO.response.CategoryResponse;

import NewsApp.entity.News;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public class NewsResponse {
    @NotNull
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String title;

    @NotBlank
    private String content;

    @PastOrPresent
    private LocalDateTime createdAt;

    private boolean isPublished;

    @Valid
    private NewsAuthorDto author;

    @Valid
    private List<CategoryResponse> categories;

    @Valid
    private List<NewsCommentDto> comment;

    // Геттеры и сеттеры

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public NewsAuthorDto getAuthor() {
        return author;
    }

    public List<CategoryResponse> getCategories() {
        return categories;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public void setAuthor(NewsAuthorDto author) {
        this.author = author;
    }

    public void setCategories(List<CategoryResponse> categories) {
        this.categories = categories;
    }

    public List<NewsCommentDto> getComment() {
        return comment;
    }

    public void setComment(List<NewsCommentDto> comment) {
        this.comment = comment;
    }
}
