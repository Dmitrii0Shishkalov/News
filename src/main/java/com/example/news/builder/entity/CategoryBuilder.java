package com.example.news.builder.entity;

import com.example.news.entity.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CategoryBuilder {
    private Long id;
    private String name;
    private String slug;
    private LocalDateTime createdAt;
    private List<NewsCategory> newsCategories = new ArrayList<>();

    public CategoryBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public CategoryBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CategoryBuilder slug(String slug) {
        this.slug = slug;
        return this;
    }

    public CategoryBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public CategoryBuilder newsCategories(List<NewsCategory> newsCategories) {
        this.newsCategories = newsCategories;
        return this;
    }

    public CategoryBuilder addNewsCategory(NewsCategory newsCategory) {
        if (this.newsCategories == null) {
            this.newsCategories = new ArrayList<>();
        }
        this.newsCategories.add(newsCategory);
        return this;
    }

    public Category build() {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        category.setSlug(slug);
        category.setCreatedAt(createdAt);
        category.setNewsCategories(newsCategories);
        return category;
    }


}
