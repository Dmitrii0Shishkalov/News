package com.example.news.service;

import com.example.news.DTO.request.News.NewsCreateRequest;
import com.example.news.DTO.request.News.NewsUpdateRequest;
import com.example.news.DTO.response.news.NewsResponse;
import com.example.news.exception.ResourceNotFoundException;
import com.example.news.mapper.NewsMapper;
import com.example.news.entity.*;
import com.example.news.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;

import java.util.List;
import java.util.Set;


@Service
public class NewsService {
    private static final Logger log = LoggerFactory.getLogger(NewsService.class);
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final UserService userService;
    private final CategoryService categoryService;
    private final NewsCategoryService newsCategoryService;


    public NewsService(NewsRepository newsRepository, NewsMapper newsMapper, UserService userService, CategoryService categoryService, NewsCategoryService newsCategoryService) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
        this.userService = userService;
        this.categoryService = categoryService;
        this.newsCategoryService = newsCategoryService;
    }


    public List<NewsResponse> findAllNews() {
        return newsMapper.toDtoList(newsRepository.findAll());
    }
    public List<NewsResponse> findNewsByCategoryId(Set<Long> categoryIds) {
        // Используем метод с In
        return newsRepository.findByNewsCategories_Category_IdIn(categoryIds)
                .stream()
                .map(newsMapper::toDto)
                .toList();

        // Или с использованием JPQL
        // return newsRepository.findNewsByCategoryIds(categoryIds)
        //        .stream()
        //        .map(newsMapper::toDto)
        //        .toList();
    }
    @Transactional
    public NewsResponse createNews(NewsCreateRequest request, Long authorId) throws AccessDeniedException {
        User author = userService.getUserById(authorId);
        if (!author.getRole().equals(User.Role.AUTHOR)) {
            throw new AccessDeniedException("Only authors can create news");
        }
        News news = newsMapper.toEntity(request);
        news = newsRepository.save(news);  // Сначала сохраняем, чтобы получить id
        categoryService.addSelectedCategoriesToNews(news.getId(), request.getCategoryIds());
        return newsMapper.toDto(news);
    }

    @Transactional
    public NewsResponse updateNews(Long id, NewsUpdateRequest request, Long authorId) throws AccessDeniedException {
        News news = findNewsByIdAndAuthor(id, authorId);
        updateNewsFields(news, request);
        newsCategoryService.updateNewsCategories(news, request.getCategories());
        return newsMapper.toDto(newsRepository.save(news));
    }

    @Transactional
    public void deleteNews(Long id) {
        if (!newsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Статья с id:" + id);
        }
        newsRepository.deleteById(id);
        log.info("Удалён статья ID: {}", id);

    }

    private News findNewsByIdAndAuthor(Long id, Long authorId) throws AccessDeniedException {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News "));
        if (!news.getAuthor().getId().equals(authorId)) {
            throw new AccessDeniedException("You are not the author");
        }
        return news;
    }

    private void updateNewsFields(News news, NewsUpdateRequest request) {
        if (request.getTitle() != null) news.setTitle(request.getTitle());
        if (request.getContent() != null) news.setContent(request.getContent());
    }
    public News getNewsById(Long newsId) {
        return newsRepository.findById(newsId)
                .orElseThrow(() -> new ResourceNotFoundException("News" + newsId));
    }


}