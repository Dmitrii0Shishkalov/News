package com.example.news.mapper;

import com.example.news.DTO.request.Comments.CommentsRequest;
import com.example.news.DTO.response.comments.CommentNewsResponse;
import com.example.news.DTO.response.comments.CommentResponse;
import com.example.news.DTO.response.comments.CommentUserResponse;
import com.example.news.builder.entity.CommentBuilder;
import com.example.news.entity.Comment;
import com.example.news.entity.News;
import com.example.news.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-08T16:06:00+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class CommentsMapperImpl implements CommentsMapper {

    @Override
    public CommentResponse toDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentResponse commentResponse = new CommentResponse();

        commentResponse.setUser( userToUserResponse( comment.getUser() ) );
        commentResponse.setNews( newsToNewsResponse( comment.getNews() ) );
        commentResponse.setId( comment.getId() );
        commentResponse.setContent( comment.getContent() );
        commentResponse.setCreatedAt( comment.getCreatedAt() );
        commentResponse.setUpdatedAt( comment.getUpdatedAt() );

        return commentResponse;
    }

    @Override
    public CommentUserResponse userToUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        CommentUserResponse commentUserResponse = new CommentUserResponse();

        commentUserResponse.setUsername( user.getUsername() );
        commentUserResponse.setId( user.getId() );

        return commentUserResponse;
    }

    @Override
    public CommentNewsResponse newsToNewsResponse(News news) {
        if ( news == null ) {
            return null;
        }

        CommentNewsResponse commentNewsResponse = new CommentNewsResponse();

        commentNewsResponse.setTitle( news.getTitle() );
        commentNewsResponse.setId( news.getId() );

        return commentNewsResponse;
    }

    @Override
    public Comment toEntity(CommentsRequest request) {
        if ( request == null ) {
            return null;
        }

        CommentBuilder comment = Comment.builder();

        comment.content( request.getContent() );

        comment.createdAt( java.time.LocalDateTime.now() );
        comment.updatedAt( java.time.LocalDateTime.now() );

        return comment.build();
    }

    @Override
    public Comment fromRequest(CommentsRequest request) {
        if ( request == null ) {
            return null;
        }

        CommentBuilder comment = Comment.builder();

        comment.content( request.getContent() );

        return comment.build();
    }
}
