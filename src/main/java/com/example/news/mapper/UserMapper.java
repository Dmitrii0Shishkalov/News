package com.example.news.mapper;

import com.example.news.DTO.response.*;

import com.example.news.entity.User;
import org.mapstruct.*;


import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {NewsMapper.class})
public interface UserMapper {

    @Mapping(target = "news", source = "news")
    UserResponse toDto(User user);

    default List<UserResponse> toDtoList(List<User> users) {
        return users.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}