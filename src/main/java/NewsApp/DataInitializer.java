package NewsApp;

import NewsApp.security.service.PasswordMigrationService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final PasswordMigrationService passwordMigrationService;

    public DataInitializer(PasswordMigrationService passwordMigrationService) {
        this.passwordMigrationService = passwordMigrationService;
    }

//    @PostConstruct  // Метод выполнится после создания бина
//    public void init() {
//        passwordMigrationService.migratePasswords();
//    }
}