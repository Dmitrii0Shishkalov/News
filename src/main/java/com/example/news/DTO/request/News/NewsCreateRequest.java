package com.example.news.DTO.request.News;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;


public class NewsCreateRequest {
    @NotBlank(message = "Title cannot be blank")
    @Size(max = 255, message = "Title must be less than 255 characters")
    private String title;

    @NotBlank(message = "Content cannot be blank")
    @Size(min = 10, message = "Content must be at least 10 characters long")
    private String content;

    @Size(max = 2048, message = "Image URL must be less than 2048 characters")
    private String image;

    private boolean isPublished;

    @NotNull(message = "Category IDs cannot be null")
    @Size(min = 1, message = "At least one category must be specified")
    private List<@NotNull Long> categoryIds;


    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImage() {
        return image;
    }

    public boolean getIsPublished() {
        return isPublished;
    }

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }

}