package NewsApp.security.service;


import NewsApp.entity.User;
import NewsApp.repository.UserRepository;
import NewsApp.security.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    log.error("User not found: {}", username);
                    return new UsernameNotFoundException("User not found: " + username);
                });

        if (user.getPassword() == null) {
            log.error("No password set for user: {}", username);
            throw new BadCredentialsException("No password set");
        }

        if (!user.getPassword().startsWith("$2a$")) {
            log.error("Invalid password encoding for user: {}", username);
            throw new BadCredentialsException("Invalid password encoding");
        }

        log.info("Successfully loaded user: {}", username);
        return new CustomUserDetails(user);
    }
}