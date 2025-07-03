package NewsApp.service;

import NewsApp.DTO.request.News.NewsCreateRequest;
import NewsApp.DTO.request.News.NewsUpdateRequest;
import NewsApp.DTO.response.news.NewsResponse;
import NewsApp.entity.News;
import NewsApp.entity.User;
import NewsApp.exception.ResourceNotFoundException;
import NewsApp.mapper.NewsMapper;
import NewsApp.repository.NewsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
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
    }
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN', 'AUTHOR')")  // Теперь и ADMIN, и AUTHOR могут создавать
    public NewsResponse createNews(NewsCreateRequest request, User user) throws AccessDeniedException {  // Переименуем author → user
        // Проверяем, что пользователь AUTHOR или ADMIN
        if (!user.getRole().equals(User.Role.AUTHOR) && !user.getRole().equals(User.Role.ADMIN)) {
            throw new AccessDeniedException("Только авторы и администраторы могут создавать новости");
        }

        News news = newsMapper.toEntity(request);
        news.setAuthor(user);  // Устанавливаем автора (им может быть и ADMIN)
        news = newsRepository.save(news);

        if (request.getCategoryIds() != null && !request.getCategoryIds().isEmpty()) {
            categoryService.addSelectedCategoriesToNews(news.getId(), request.getCategoryIds());
        }

        return newsMapper.toDto(news);
    }

    @Transactional
    public NewsResponse updateNews(Long id, NewsUpdateRequest request, Long userId, User.Role userRole) throws AccessDeniedException {
        News news = findNewsByIdAndCheckPermissions(id, userId, userRole);  // Используем новый метод
        updateNewsFields(news, request);
        newsCategoryService.updateNewsCategories(news, request.getCategories());
        return newsMapper.toDto(newsRepository.save(news));
    }

    @Transactional
    public void deleteNews(Long id, Long userId, User.Role userRole) throws AccessDeniedException {
        News news = findNewsByIdAndCheckPermissions(id, userId, userRole);  // Используем новый метод
        newsRepository.delete(news);
        log.info("Удалена статья ID: {}", id);
    }
    public boolean isNewsAuthor(Long newsId, String username) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new ResourceNotFoundException("Новость не найдена "));
        User user = userService.getUserByUsername(username);
        return news.getAuthor().getId().equals(user.getId()) || user.getRole() == User.Role.ADMIN;
    }

    private News findNewsByIdAndCheckPermissions(Long id, Long userId, User.Role userRole) throws AccessDeniedException {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Новость не найдена"));

        // Если пользователь не админ И не автор - выбрасываем исключение
        if (!userRole.equals(User.Role.ADMIN) && !news.getAuthor().getId().equals(userId)) {
            throw new AccessDeniedException("У вас нет прав доступа к этой новости!");
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