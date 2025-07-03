package NewsApp.security.DTO.response;

public record UserResponse(
        Long id,
        String username,
        String email,
        String firstName,
        String lastName,
        String role
) {}
