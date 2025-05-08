package com.example.news.service;

import com.example.news.DTO.request.CategoryRequest;
import com.example.news.DTO.response.CategoryResponse;
import com.example.news.entity.*;
import com.example.news.exception.CategoriesNotFoundException;
import com.example.news.exception.ResourceNotFoundException;
import com.example.news.mapper.CategoryMapper;
import com.example.news.repository.CategoryRepository;
import com.example.news.repository.NewsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {
    private static final Logger log = LoggerFactory.getLogger(CategoryService.class);
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private  final NewsRepository newsRepository;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper, NewsRepository newsRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.newsRepository = newsRepository;
    }

    // GET
    @Transactional(readOnly = true)
    public List<CategoryResponse> getAllCategories() {
        return categoryMapper.toDtoList(categoryRepository.findAll());
    }

    // POST
    @Transactional
    public CategoryResponse createCategory(CategoryRequest request) {
        Category category = Category.builder()
                .name(request.getName())
                .slug(request.getSlug())
                .build();
        Category savedCategory = categoryRepository.save(category);
        log.info("Created category with ID: {}", savedCategory.getId());
        return categoryMapper.toDto(savedCategory);
    }

    // DELETE
    @Transactional
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Категория не найдена с id: " + id);
        }
        categoryRepository.deleteById(id);
        log.info("Удалена категория ID: {}", id);
    }

    @Transactional
    public void addSelectedCategoriesToNews(Long newsId, List<Long> categoryIds) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new EntityNotFoundException("News not found with id: " + newsId));

        List<Category> categories = categoryRepository.findAllById(categoryIds);

        // Проверяем, что все категории найдены
        if (categories.size() != categoryIds.size()) {
            List<Long> foundIds = categories.stream().map(Category::getId).toList();
            List<Long> notFoundIds = categoryIds.stream()
                    .filter(id -> !foundIds.contains(id))
                    .toList();
            throw new CategoriesNotFoundException(notFoundIds); // Заменяем здесь
        }

        categories.forEach(news::addCategory);
    }

    @Transactional(readOnly = true)
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() ->  new ResourceNotFoundException("Категория не найдена с id: " + id));
    }
}