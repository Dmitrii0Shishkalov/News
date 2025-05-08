package com.example.news.DTO.response;


import com.example.news.builder.dto.CategoryDtoBuilder;
import com.example.news.entity.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CategoryResponse {
    @NotNull(message = "ID категории не может быть null")
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Pattern(regexp = "^[a-z0-9-]+$")
    private String slug;


    public static CategoryDtoBuilder builder(){
        return  new CategoryDtoBuilder();
    }

    public static CategoryResponse fromEntity(Category category) {
        CategoryResponse dto = new CategoryResponse();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setSlug(category.getSlug());
        return dto;}


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
