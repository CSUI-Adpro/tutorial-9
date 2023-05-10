package id.ac.ui.cs.advprog.tutorial9.controller;

import id.ac.ui.cs.advprog.tutorial9.model.Category;
import id.ac.ui.cs.advprog.tutorial9.service.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private CategoryController categoryController;

    @MockBean
    private CategoryServiceImpl service;

    Category category;
    List<Category> categories;

    @BeforeEach
    void setUp() {
        category = new Category("Random");
        categories = new ArrayList<>();
        categories.add(category);
    }

    @Test
    void getListCategory() throws Exception {
        // Given
        when(service.getListCategory()).thenReturn(categories);
        // When
        ResponseEntity<Iterable<Category>> responseEntity = categoryController.getListCategory();
        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(categories, responseEntity.getBody());

    }

    @Test
    void getCategory() throws Exception {
        // Given
        when(service.getCategoryById(1)).thenReturn(category);
        // When
        ResponseEntity responseEntity = categoryController.getCategory(1);
        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(category, responseEntity.getBody());
    }

    @Test
    void getCategoryNotFound() throws Exception {
        // Given
        when(service.getCategoryById(2)).thenReturn(null);
        // When
        ResponseEntity responseEntity = categoryController.getCategory(2);
        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }
}