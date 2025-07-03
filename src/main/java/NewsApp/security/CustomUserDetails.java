package NewsApp.security;

import NewsApp.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Учитываем роль пользователя
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
    }

    @Override
    public String getPassword() {
        // Возвращаем реальный пароль из User
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Или реализуйте логику из вашего User
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Или реализуйте логику из вашего User
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Или реализуйте логику из вашего User
    }

    @Override
    public boolean isEnabled() {
        return true; // Или реализуйте логику из вашего User
    }

    public User getUser() {
        return user;
    }
}