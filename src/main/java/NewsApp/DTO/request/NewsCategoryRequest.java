package NewsApp.DTO.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class NewsCategoryRequest {
    @NotNull(message = "Список categoryIds не может быть null")
    @Size(min = 1, message = "Должен быть указан хотя бы один categoryId")
    private List<@NotNull(message = "ID категории не может быть null") Long> categoryIds;

    // Геттеры и сеттеры
    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }
}
