package NewsApp.repository;

import NewsApp.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findAllByAuthorId(Long authorId);
    List<News> findAllByIsPublished(boolean isPublished);
    List<News> findAllByAuthorIdAndIsPublished(Long authorId, boolean isPublished);

    List<News> findByNewsCategories_Category_Id(Set<Long> categoryId);
    List<News> findByNewsCategories_Category_IdIn(Set<Long> categoryIds);

}
