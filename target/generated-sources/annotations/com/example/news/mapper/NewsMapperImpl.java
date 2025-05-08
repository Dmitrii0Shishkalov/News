package com.example.news.mapper;

import com.example.news.DTO.request.News.NewsCreateRequest;
import com.example.news.DTO.response.news.NewsAuthorDto;
import com.example.news.DTO.response.news.NewsCommentDto;
import com.example.news.DTO.response.news.NewsResponse;
import com.example.news.builder.dto.NewsAuthorBuilder;
import com.example.news.builder.entity.NewsBuilder;
import com.example.news.entity.Comment;
import com.example.news.entity.News;
import com.example.news.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-08T16:06:00+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class NewsMapperImpl implements NewsMapper {

    @Override
    public NewsResponse toDto(News news) {
        if ( news == null ) {
            return null;
        }

        NewsResponse newsResponse = new NewsResponse();

        newsResponse.setAuthor( userToNewsAuthorDto( news.getAuthor() ) );
        newsResponse.setCategories( mapNewsCategoriesToCategories( news.getNewsCategories() ) );
        newsResponse.setComment( commentListToNewsCommentDtoList( news.getComments() ) );
        newsResponse.setId( news.getId() );
        newsResponse.setTitle( news.getTitle() );
        newsResponse.setContent( news.getContent() );
        newsResponse.setCreatedAt( news.getCreatedAt() );
        newsResponse.setPublished( news.isPublished() );

        return newsResponse;
    }

    @Override
    public News toEntity(NewsCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        NewsBuilder news = News.builder();

        news.title( request.getTitle() );
        news.content( request.getContent() );
        news.image( request.getImage() );
        news.isPublished( request.getIsPublished() );

        return news.build();
    }

    protected NewsAuthorDto userToNewsAuthorDto(User user) {
        if ( user == null ) {
            return null;
        }

        NewsAuthorBuilder newsAuthorDto = NewsAuthorDto.builder();

        newsAuthorDto.id( user.getId() );
        newsAuthorDto.username( user.getUsername() );
        newsAuthorDto.email( user.getEmail() );

        return newsAuthorDto.build();
    }

    protected NewsCommentDto commentToNewsCommentDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        NewsCommentDto newsCommentDto = new NewsCommentDto();

        newsCommentDto.setContent( comment.getContent() );
        newsCommentDto.setId( comment.getId() );

        return newsCommentDto;
    }

    protected List<NewsCommentDto> commentListToNewsCommentDtoList(List<Comment> list) {
        if ( list == null ) {
            return null;
        }

        List<NewsCommentDto> list1 = new ArrayList<NewsCommentDto>( list.size() );
        for ( Comment comment : list ) {
            list1.add( commentToNewsCommentDto( comment ) );
        }

        return list1;
    }
}
