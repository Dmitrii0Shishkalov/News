package NewsApp.service;


import NewsApp.DTO.request.Comments.CommentUpdateRequest;
import NewsApp.DTO.request.Comments.CommentsRequest;
import NewsApp.DTO.response.comments.CommentResponse;
import NewsApp.entity.Comment;
import NewsApp.entity.News;
import NewsApp.entity.User;
import NewsApp.exception.ResourceNotFoundException;
import NewsApp.mapper.CommentsMapper;
import NewsApp.repository.CommentsRepository;
import NewsApp.repository.NewsRepository;
import NewsApp.repository.UserRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class CommentsService {
    private static final Logger log = LoggerFactory.getLogger(CommentsService.class);
    private final NewsRepository newsRepository;
    private final CommentsRepository commentsRepository;
    private final UserRepository userRepository;
    private final CommentsMapper commentsMapper;
    public CommentsService(NewsRepository newsRepository, CommentsRepository commentsRepository, UserRepository userRepository, CommentsMapper commentsMapper) {
        this.newsRepository = newsRepository;
        this.commentsRepository = commentsRepository;
        this.userRepository = userRepository;
        this.commentsMapper = commentsMapper;
    }



    //@GET
    public List<CommentResponse> findAllComments() {
        List<Comment> comments = commentsRepository.findAll(); // Получаем все комментарии из БД
        return comments.stream()
                .map(commentsMapper::toDto)
                .toList();
    }

    @Transactional
    public CommentResponse createComment(CommentsRequest request, Long newsId, User author) {
        log.info("Creating comment with content: {}", request.getContent());

        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> {
                    log.error("News not found with id: {}", newsId);
                    return new ResourceNotFoundException("News not found");
                });

        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setUser(author);
        comment.setNews(news);
        comment.setCreatedAt(LocalDateTime.now());

        news.getComments().add(comment);
        comment = commentsRepository.save(comment); // Сохраняем отдельно

        log.info("Created comment with id: {}", comment.getId());

        return commentsMapper.toDto(comment);
    }

   //PUT
   @Transactional
   public CommentResponse updateComment(Long id, CommentUpdateRequest request) {
       Comment comment = commentsRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Comment "));

       comment.updateContent(request.getContent()); // делегируем логику в сущность

       return commentsMapper.toDto(comment); // маппинг остаётся в сервисе
   }

    //Delete
    @Transactional
    public void deleteComment(Long id) {
        Comment comment = commentsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment "));

        News news = comment.getNews();
            news.removeComment(comment); // синхронизируем связи

        // commentsRepository.delete(comment); // не нужно, если есть orphanRemoval
    }
}
