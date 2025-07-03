package NewsApp.builder.entity;

import NewsApp.entity.*;

import java.time.LocalDateTime;

public class NewsCategoryBuilder {
    private Long id;
    private News news;
    private Category category;
    private LocalDateTime assignedAt;

    public NewsCategoryBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public NewsCategoryBuilder news(News news) {
        this.news = news;
        return this;
    }

    public NewsCategoryBuilder category(Category category) {
        this.category = category;
        return this;
    }

    public NewsCategoryBuilder assignedAt(LocalDateTime assignedAt) {
        this.assignedAt = assignedAt;
        return this;
    }

    public NewsCategory build() {
        NewsCategory newsCategory = new NewsCategory();
        newsCategory.setId(id);
        newsCategory.setNews(news);
        newsCategory.setCategory(category);
        newsCategory.setAssignedAt(assignedAt != null ? assignedAt : LocalDateTime.now());
        return newsCategory;
    }
}