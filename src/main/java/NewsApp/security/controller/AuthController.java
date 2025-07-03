package NewsApp.security.controller;

import NewsApp.entity.User;
import NewsApp.security.CustomUserDetails;
import NewsApp.security.DTO.request.LoginRequest;
import NewsApp.security.DTO.request.RefreshTokenRequest;
import NewsApp.security.DTO.request.RegisterRequest;
import NewsApp.security.DTO.response.JwtResponse;
import NewsApp.security.jwt.JwtUtils;
import NewsApp.security.service.AuthService;
import NewsApp.security.service.UserDetailsServiceImpl;
import NewsApp.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final AuthService authService;
    private  final UserDetailsServiceImpl  userDetailsServiceImpl;

    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, AuthService authService, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.authService = authService;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        log.info("Login attempt for user: {}", request.username());

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.username(),
                            request.password()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            log.info("User authenticated: {}", userDetails.getUsername());

            String jwt = jwtUtils.generateToken(userDetails);
            String refreshToken = jwtUtils.generateRefreshToken(userDetails);

            User user = authService.findByUsername(userDetails.getUsername())
                    .orElseThrow(() -> {
                        log.error("User not found after authentication: {}", userDetails.getUsername());
                        return new UsernameNotFoundException("User not found after authentication");
                    });

            return ResponseEntity.ok(new JwtResponse(jwt, refreshToken, user));

        } catch (BadCredentialsException e) {
            log.warn("Invalid credentials for user: {}", request.username(), e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Неверный логин или пароль");
        } catch (Exception e) {
            log.error("Authentication error for user: {}", request.username(), e);
            return ResponseEntity.internalServerError()
                    .body("Ошибка сервера при аутентификации: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        if (authService.existsByUsername(request.username())) {
            return ResponseEntity.badRequest().body("Имя пользователя уже существует");
        }

        authService.registerUser(request);
        return ResponseEntity.ok("Пользователь успешно зарегистрировался");
    }
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        try {
            String refreshToken = request.refreshToken();

            if (jwtUtils.isRefreshTokenValid(refreshToken)) {
                String username = jwtUtils.extractUsername(refreshToken);
                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);

                String newAccessToken = jwtUtils.generateToken(userDetails);
                String newRefreshToken = jwtUtils.generateRefreshToken(userDetails);

                User user = authService.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

                return ResponseEntity.ok(new JwtResponse(newAccessToken, newRefreshToken, user));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error refreshing token");
        }
    }
}
