package NewsApp.controller;

import NewsApp.DTO.request.News.NewsCreateRequest;
import NewsApp.DTO.request.News.NewsUpdateRequest;
import NewsApp.DTO.response.news.NewsResponse;
import NewsApp.entity.User;
import NewsApp.repository.NewsRepository;
import NewsApp.security.CustomUserDetails;
import NewsApp.service.NewsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Set;

@ControllerAdvice
@RestController
@RequestMapping("/api/news")
public class NewsController {
    private final NewsService newsService;
    private final NewsRepository newsRepository;

    public NewsController(NewsService newsService, NewsRepository newsRepository) {
        this.newsService = newsService;
        this.newsRepository = newsRepository;
    }

    @GetMapping
    public List<NewsResponse> findAllNews(
            @RequestParam(required = false) @Size(min = 1) Set<@NotNull Long> categoryId) {
        if (categoryId != null && !categoryId.isEmpty()) {
            return newsService.findNewsByCategoryId(categoryId);
        }
        return newsService.findAllNews();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ADMIN', 'AUTHOR')")  // Разрешаем и ADMIN, и AUTHOR
    public NewsResponse createNews(
            @RequestBody @Valid NewsCreateRequest request,
            Authentication authentication) throws AccessDeniedException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();  // Переименовали author → user для ясности
        return newsService.createNews(request, user);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @newsService.isNewsAuthor(#id, authentication.name)")
    public NewsResponse updateNews(
            @PathVariable @Min(1) Long id,
            @RequestBody @Valid NewsUpdateRequest request,
            Authentication authentication) throws AccessDeniedException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();
        return newsService.updateNews(id, request, user.getId(), user.getRole());  // Добавляем роль
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @newsService.isNewsAuthor(#id, authentication.name)")
    public void deleteNews(
            @PathVariable @Min(1) Long id,
            Authentication authentication) throws AccessDeniedException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();
        newsService.deleteNews(id, user.getId(), user.getRole());  // Добавляем роль
    }
}