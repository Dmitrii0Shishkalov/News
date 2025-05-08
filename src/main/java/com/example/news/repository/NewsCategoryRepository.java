package com.example.news.repository;

import com.example.news.entity.News;
import com.example.news.entity.NewsCategory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsCategoryRepository extends JpaRepository<NewsCategory, Long> {
    @Modifying
    @Query("DELETE FROM NewsCategory nc WHERE nc.news.id = :newsId")
    void deleteByNewsId(@Param("newsId") Long newsId);

    @Query("SELECT nc FROM NewsCategory nc JOIN FETCH nc.category WHERE nc.news.id = :newsId")
//    List<NewsCategory> findByNewsIdWithCategory(@Param("newsId") Long newsId);
    @EntityGraph(attributePaths = "categories")
    List<News> findAllWithCategories();
}
