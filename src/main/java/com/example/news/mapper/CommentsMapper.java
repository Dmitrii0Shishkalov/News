package com.example.news.mapper;

import com.example.news.DTO.request.Comments.CommentsRequest;
import com.example.news.DTO.response.comments.*;
import com.example.news.entity.Comment;
import com.example.news.entity.News;
import com.example.news.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CommentsMapper {
    @Mapping(target = "user", source = "user")
    @Mapping(target = "news", source = "news")
    CommentResponse toDto(Comment comment);

    CommentUserResponse userToUserResponse(User user);
    CommentNewsResponse newsToNewsResponse(News news);

    @Mapping(target = "id", ignore = true) // ID генерируется БД
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "user", ignore = true) // Пользователь должен быть установлен отдельно
    @Mapping(target = "news", ignore = true) // Новость должна быть установлена отдельно
    Comment toEntity(CommentsRequest request);

    // Обратный маппинг для создания
    @Mapping(target = "user", ignore = true) // Игнорируем при создании
    @Mapping(target = "news", ignore = true)
    Comment fromRequest(CommentsRequest request);
}