package com.example.news.DTO.request;

import com.example.news.builder.dto.CategoryDtoBuilder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CategoryRequest {
    private Long id;

    @NotBlank(message = "Название категории не может быть пустым")
    @Size(max = 100, message = "Название категории должно быть короче 100 символов")
    private String name;

    @NotBlank(message = "Slug не может быть пустым")
    @Pattern(regexp = "^[a-z0-9-]+$", message = "Slug может содержать только строчные буквы, цифры и дефисы")
    private String slug;

        public static CategoryDtoBuilder builder(){
            return  new CategoryDtoBuilder();
        }
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


