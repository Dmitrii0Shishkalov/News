package NewsApp.DTO.response.comments;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CommentNewsResponse {
    @NotNull
    private Long id;

    @NotBlank
    private String title;

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(Long id) {
        this.id = id;
    }
}