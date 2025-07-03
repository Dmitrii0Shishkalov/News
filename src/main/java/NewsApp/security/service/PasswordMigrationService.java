package NewsApp.security.service;

import NewsApp.entity.User;
import NewsApp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PasswordMigrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public PasswordMigrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void migratePasswords() {
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            if (!user.getPassword().startsWith("$2a$")) { // Проверяем, не закодирован ли уже пароль
                String encodedPassword = passwordEncoder.encode(user.getPassword());
                user.setPassword(encodedPassword);
                userRepository.save(user);
            }
        });
    }
}