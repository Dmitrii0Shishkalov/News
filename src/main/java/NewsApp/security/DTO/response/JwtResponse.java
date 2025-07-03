package NewsApp.security.DTO.response;

import NewsApp.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public record JwtResponse(
        String token,
        String refreshToken,
        String type,       // "Bearer"
        Long id,
        String username,
        String role
) {
    public JwtResponse(String token,  String refreshToken, User user) {
        this(token, refreshToken,"Bearer",  user.getId(), user.getUsername(), user.getRole().name());
    }
}