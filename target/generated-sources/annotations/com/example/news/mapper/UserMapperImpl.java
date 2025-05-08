package com.example.news.mapper;

import com.example.news.DTO.response.UserResponse;
import com.example.news.builder.dto.UserResponseBuilder;
import com.example.news.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-08T16:06:00+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public UserResponse toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.news( newsMapper.toDtoList( user.getNews() ) );
        userResponse.id( user.getId() );
        userResponse.username( user.getUsername() );
        if ( user.getRole() != null ) {
            userResponse.role( user.getRole().name() );
        }
        userResponse.password( user.getPassword() );
        userResponse.email( user.getEmail() );

        return userResponse.build();
    }
}
