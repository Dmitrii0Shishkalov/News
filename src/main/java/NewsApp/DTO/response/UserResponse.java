package NewsApp.DTO.response;

import NewsApp.DTO.response.news.NewsResponse;
import NewsApp.builder.dto.UserResponseBuilder;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class UserResponse {
    @NotNull
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @Email
    private String email;

    // Пароль обычно не возвращается в Response, но если нужно:
    @Size(min = 8)
    private String password;

    @NotBlank
    private String role;

    // Обычно не валидируем вложенные объекты в Response
    private List<NewsResponse> news;

    public static UserResponseBuilder builder() {
        return new UserResponseBuilder();
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNews(List<NewsResponse> news) {
        this.news = news;
    }

    public List<NewsResponse> getNews() {
        return news;
    }
}

