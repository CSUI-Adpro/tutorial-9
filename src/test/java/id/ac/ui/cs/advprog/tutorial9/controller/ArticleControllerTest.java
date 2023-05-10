package id.ac.ui.cs.advprog.tutorial9.controller;

import id.ac.ui.cs.advprog.tutorial9.model.Article;
import id.ac.ui.cs.advprog.tutorial9.model.ArticleView;
import id.ac.ui.cs.advprog.tutorial9.model.Category;
import id.ac.ui.cs.advprog.tutorial9.service.ArticleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = ArticleController.class)
class ArticleControllerTest {

    @Autowired
    private ArticleController articleController;

    @MockBean
    private ArticleServiceImpl service;

    Category category;
    Article article;
    List<Article> articles;
    ArticleView articleView;
    List<ArticleView> articleViews;

    @BeforeEach
    void setUp() {
        Date date = new Date();
        category = new Category("Random");
        article = new Article("Title", "Content", category, date);
        articles = new ArrayList<>();
        articles.add(article);
        articleView = new ArticleView("123.456.789", date, article);
        articleViews = new ArrayList<>();
        articleViews.add(articleView);
    }


    @Test
    void testGetArticleViewByDate() throws Exception {
        // Given
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = formatter.format(currentDate);
        int page = 1;
        int limit = 10;
        when(service.getArticleViewByDate(formattedDate, page, limit)).thenReturn(articleViews);

        // When
        ResponseEntity<Iterable<ArticleView>> responseEntity = articleController.getArticleViewByDate(formattedDate, page, limit);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(articleViews, responseEntity.getBody());
    }

    @Test
    void testListArticle() throws Exception {
        // Given
        int page = 1;
        int limit = 10;
        when(service.getListArticle(page, limit)).thenReturn(articles);

        // When
        ResponseEntity<Iterable<Article>> responseEntity = articleController.listArticle(page, limit);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(articles, responseEntity.getBody());
    }

    @Test
    void testGetArticleByID() throws Exception {
        // Given
        int articleID = 1;
        when(service.getArticleById(articleID)).thenReturn(articles.get(0));

        // When
        ResponseEntity responseEntity = articleController.getArticleByID(articleID);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(articles.get(0), responseEntity.getBody());
    }
}