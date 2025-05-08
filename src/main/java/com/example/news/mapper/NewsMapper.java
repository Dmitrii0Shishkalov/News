package com.example.news.mapper;

import com.example.news.DTO.request.News.NewsCreateRequest;
import com.example.news.DTO.response.CategoryResponse;
import com.example.news.DTO.response.news.NewsResponse;
import com.example.news.entity.*;

import org.mapstruct.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CategoryMapper.class})
public interface NewsMapper {
    @Mapping(target = "author", source = "author")
    @Mapping(target = "categories", source = "newsCategories", qualifiedByName = "mapNewsCategoriesToCategories")
    @Mapping(target = "comment", source = "comments")
    NewsResponse toDto(News news);

    News toEntity(NewsCreateRequest request);



    default List<NewsResponse> toDtoList(List<News> news) {
        return news.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    @Named("mapNewsCategoriesToCategories")
    default List<CategoryResponse> mapNewsCategoriesToCategories(List<NewsCategory> newsCategories) {
        if (newsCategories == null) {
            return Collections.emptyList();
        }
        return newsCategories.stream()
                .map(NewsCategory::getCategory)
                .filter(Objects::nonNull)
                .map(category -> CategoryResponse.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .slug(category.getSlug())
                        .build())
                .collect(Collectors.toList());
    }
}