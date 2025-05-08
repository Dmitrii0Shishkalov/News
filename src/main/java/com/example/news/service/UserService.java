package com.example.news.service;


import com.example.news.DTO.request.User.UserRequest;
import com.example.news.DTO.response.UserResponse;
import com.example.news.exception.DuplicateEmailException;
import com.example.news.exception.ResourceNotFoundException;
import com.example.news.mapper.UserMapper;
import com.example.news.entity.User;
import com.example.news.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private  final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь с таким  id: " + id));
    }


    // Получение всех пользователей
    @Transactional(readOnly = true)
    public List<UserResponse> findAllUsers() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    // Создание пользователя
    @Transactional
    public UserResponse createUser(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
             new DuplicateEmailException();
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(User.Role.AUTHOR)
                .createdAt(LocalDateTime.now())  // Добавлено время создания
                .build();

        User savedUser = userRepository.save(user);
        log.info("Created new user with id: {}", savedUser.getId());
        return userMapper.toDto(savedUser);
    }

    // Обновление пользователя
    @Transactional
    public void updateUser(Long id, UserRequest request) {
        User user = getUserById(id);

        if (request.getEmail() != null && !request.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmail(request.getEmail())) {
                 new DuplicateEmailException();
            }
            user.setEmail(request.getEmail());
        }

        if (request.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }

        user.setUpdatedAt(LocalDateTime.now());  // Добавлено время обновления
        log.info("Updated user with id: {}", id);
    }

    // Удаление пользователя
    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
             new ResourceNotFoundException("Пользователь с таким  id: " + id);
        }
        userRepository.deleteById(id);
        log.info("Deleted user with id: {}", id);
    }
}