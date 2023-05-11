package id.ac.ui.cs.advprog.tutorial9.repository;

import id.ac.ui.cs.advprog.tutorial9.model.Article;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    Article findById(int id);

    @Query(value = "SELECT * FROM article ORDER BY created_at DESC LIMIT ?2 OFFSET ?1", nativeQuery = true)
    List<Article> listArticle(int offset, int limit);

    @Query(value = "SELECT * FROM article WHERE article.category_id = ?1 ORDER BY created_at DESC LIMIT 1", nativeQuery = true)
    Article findArticleByCategoryId(int categoryId);

    @Query(value = "SELECT count(*) FROM article WHERE article.category_id = ?1", nativeQuery = true)
    Integer getArticleCountByCategoryId(int categoryId);
}
