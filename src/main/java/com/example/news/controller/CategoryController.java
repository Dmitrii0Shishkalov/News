package com.example.news.controller;

import com.example.news.DTO.request.CategoryRequest;
import com.example.news.DTO.response.CategoryResponse;
import com.example.news.entity.Category;
import com.example.news.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse createCategory(@RequestBody @Valid CategoryRequest request) {
//        if (categoryService.existsBySlug(request.getSlug())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Категория с таким slug уже существует");
//        }
        return categoryService.createCategory(request);
    }

    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable @Min(1) Long id) {
        categoryService.deleteCategory(id);
    }
}