package NewsApp.security.DTO.request;

import jakarta.validation.constraints.NotBlank;
import NewsApp.entity.User;

public record LoginRequest(
        @NotBlank String username,
        @NotBlank String password

) {

}
