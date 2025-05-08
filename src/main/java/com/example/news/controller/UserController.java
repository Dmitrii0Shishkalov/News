package com.example.news.controller;

import com.example.news.DTO.request.User.UserRequest;
import com.example.news.DTO.response.UserResponse;
import com.example.news.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.news.entity.User;
import com.example.news.mapper.UserMapper;

import java.util.List;
@RestController
@RequestMapping(path = "api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> findAll() {
        return userService.findAllUsers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody UserRequest request) {
        return userService.createUser(request);
    }

    @PutMapping(path = "/{id}")
    public void update(@PathVariable Long id, @Valid @RequestBody UserRequest request) {
        userService.updateUser(id, request);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
    }
}