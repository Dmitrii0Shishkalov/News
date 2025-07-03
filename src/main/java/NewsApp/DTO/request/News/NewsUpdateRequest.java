package NewsApp.DTO.request.News;

import NewsApp.DTO.response.CategoryResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

import java.util.List;

public class NewsUpdateRequest {
    @Size(min = 3, max = 255, message = "Title must be between 3 and 255 characters")
    private String title;

    @Size(min = 10, message = "Content must be at least 10 characters long")
    private String content;

    @Valid
    private List<CategoryResponse> categories;
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<CategoryResponse> getCategories() {
        return categories;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCategories(List<CategoryResponse> categories) {
        this.categories = categories;
    }
}
