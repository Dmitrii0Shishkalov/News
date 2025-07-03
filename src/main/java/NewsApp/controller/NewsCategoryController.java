package NewsApp.controller;

import NewsApp.DTO.request.NewsCategoryRequest;
import NewsApp.service.CategoryService;
import NewsApp.service.NewsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class NewsCategoryController {

    private final NewsService newsService;
    private final CategoryService categoryService;

    public NewsCategoryController(NewsService newsService, CategoryService categoryService) {
        this.newsService = newsService;
        this.categoryService = categoryService;
    }

    @PostMapping("/{newsId}/categories")
    public ResponseEntity<?> addCategoriesToNews(
            @PathVariable @Min(1) Long newsId,
            @RequestBody @Valid NewsCategoryRequest request) {

        categoryService.addSelectedCategoriesToNews(newsId, request.getCategoryIds());
        return ResponseEntity.ok().build();
    }

}