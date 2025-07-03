package NewsApp.service;


import NewsApp.DTO.response.CategoryResponse;
import NewsApp.entity.News;
import NewsApp.entity.NewsCategory;
import NewsApp.repository.CategoryRepository;
import NewsApp.repository.NewsCategoryRepository;
import NewsApp.repository.NewsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NewsCategoryService {

    private final NewsCategoryRepository newsCategoryRepository;

    private final CategoryRepository categoryRepository;

    private NewsRepository newsRepository;


    public NewsCategoryService(NewsCategoryRepository newsCategoryRepository, CategoryRepository categoryRepository,  NewsRepository newsRepository) {
        this.newsCategoryRepository = newsCategoryRepository;
        this.categoryRepository = categoryRepository;
        this.newsRepository = newsRepository;

    }

    @Transactional
    public void updateNewsCategories(News news, List<CategoryResponse> categories) {
        if (categories != null && !categories.isEmpty()) {  // Добавлена проверка на пустой список
            // Удаляем старые категории
            newsCategoryRepository.deleteByNewsId(news.getId());

            // Создаем новые связи
            List<NewsCategory> newLinks = categories.stream()
                    .map(dto -> categoryRepository.findById(dto.getId()))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .map(category -> NewsCategory.builder()
                            .news(news)
                            .category(category)
                            .assignedAt(LocalDateTime.now())
                            .build())
                    .toList();

            if (!newLinks.isEmpty()) {  // Сохраняем только если есть что сохранять
                newsCategoryRepository.saveAll(newLinks);
            }
        }
    }
    }