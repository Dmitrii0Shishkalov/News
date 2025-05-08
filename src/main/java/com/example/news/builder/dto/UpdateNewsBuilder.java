package com.example.news.builder.dto;

import com.example.news.DTO.request.News.NewsUpdateRequest;
import com.example.news.DTO.response.CategoryResponse;

import java.util.List;

public class UpdateNewsBuilder {
    private String title;
    private  String content;
    private List<CategoryResponse> categories;

    public UpdateNewsBuilder title (String title) {
        this.title = title;
        return  this;
    }
    public UpdateNewsBuilder content (String content) {
        this.content = content;
        return  this;
    }
    public UpdateNewsBuilder categories (List<CategoryResponse> categories) {
        this.categories = categories;
        return  this;
    }
    public NewsUpdateRequest builder(){
        NewsUpdateRequest updateNewsRequest = new NewsUpdateRequest();
        updateNewsRequest.setTitle(title);
        updateNewsRequest.setContent(content);
        updateNewsRequest.setCategories(categories);
        return updateNewsRequest;
    }
}
