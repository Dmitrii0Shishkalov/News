package com.example.news.mapper;

import com.example.news.DTO.response.CategoryResponse;
import com.example.news.builder.dto.CategoryDtoBuilder;
import com.example.news.entity.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-08T16:06:00+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryResponse toDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDtoBuilder categoryResponse = CategoryResponse.builder();

        categoryResponse.id( category.getId() );
        categoryResponse.name( category.getName() );
        categoryResponse.slug( category.getSlug() );

        return categoryResponse.build();
    }
}
