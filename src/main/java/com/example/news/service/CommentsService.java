package com.example.news.service;

import com.example.news.DTO.request.Comments.CommentUpdateRequest;
import com.example.news.DTO.request.Comments.CommentsRequest;
import com.example.news.DTO.response.comments.CommentResponse;
import com.example.news.entity.Comment;
import com.example.news.entity.News;
import com.example.news.entity.User;
import com.example.news.exception.ResourceNotFoundException;
import com.example.news.mapper.CommentsMapper;
import com.example.news.repository.CommentsRepository;
import com.example.news.repository.NewsRepository;
import com.example.news.repository.UserRepository;
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
    public CommentResponse createComment(CommentsRequest request, Long newsId) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new ResourceNotFoundException("News "));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User"));

        Comment comment = commentsMapper.toEntity(request);
        comment.setUser(user);
        news.addComment(comment);  // связь устанавливается здесь

        // комментарий сохранится автоматически благодаря cascade
        newsRepository.save(news);

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
