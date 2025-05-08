package com.example.news.controller;

import com.example.news.DTO.request.News.NewsCreateRequest;
import com.example.news.DTO.request.News.NewsUpdateRequest;
import com.example.news.DTO.response.news.NewsResponse;
import com.example.news.repository.NewsRepository;
import com.example.news.service.NewsService ;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Set;

@ControllerAdvice
@RestController
@RequestMapping("/api/news")
public class NewsController {
    private final NewsService newsService;
    private  final NewsRepository newsRepository;

    public NewsController(NewsService newsService, NewsRepository newsRepository) {
        this.newsService = newsService;
        this.newsRepository = newsRepository;
    }

    @GetMapping
    public List<NewsResponse> findAllNews(
            @RequestParam(required = false) @Size(min = 1) Set<@NotNull Long> categoryId){
        if (categoryId != null && !categoryId.isEmpty())
            return newsService.findNewsByCategoryId(categoryId);

        return newsService.findAllNews();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewsResponse createNews(
            @RequestBody @Valid NewsCreateRequest request,
            @RequestParam @NotNull Long authorId) throws AccessDeniedException {
        return newsService.createNews(request, authorId);
    }

    @PutMapping("/{id}")
    public NewsResponse updateNews(
            @PathVariable @Min(1) Long id,
            @RequestBody @Valid NewsUpdateRequest request,
            @RequestParam @NotNull Long authorId) throws AccessDeniedException {
        return newsService.updateNews(id, request, authorId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNews(
            @PathVariable @Min(1) Long id) {
        newsService.deleteNews(id);
    }
}