package NewsApp.security.service;

import NewsApp.entity.User;
import NewsApp.mapper.UserMapper;
import NewsApp.repository.UserRepository;
import NewsApp.security.DTO.request.RegisterRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);  // уже Optional<User>
    }

    public User registerUser(RegisterRequest request) {
        if (existsByUsername(request.username())) {
            throw new IllegalArgumentException("Username already exists");
        }

        if (existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));

        user.setRole(User.Role.AUTHOR);

        return userRepository.save(user);
    }
}
