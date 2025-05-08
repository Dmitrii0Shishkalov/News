package com.example.news.builder.dto;

import com.example.news.DTO.response.CategoryResponse;

public class CategoryDtoBuilder {
    private Long id;
    private String name;
    private String slug;


    public CategoryDtoBuilder id(Long id) {
        this.id = id;
        return this;
    }
    public CategoryDtoBuilder slug(String slug) {
        this.slug = slug;
        return this;
    }
    public CategoryDtoBuilder name(String name) {
        this.name = name;
        return this;
    }
    public CategoryResponse build(){
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(id);
        categoryResponse.setSlug(slug);
        categoryResponse.setName(name);
        return categoryResponse;
    }

}
