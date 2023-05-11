package id.ac.ui.cs.advprog.tutorial9.service;

import id.ac.ui.cs.advprog.tutorial9.model.Article;
import id.ac.ui.cs.advprog.tutorial9.model.Category;
import id.ac.ui.cs.advprog.tutorial9.repository.ArticleRepository;
import id.ac.ui.cs.advprog.tutorial9.repository.CategoryRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Iterable<Category> getListCategory() {
        List<Category> allCat = categoryRepository.findAll();

        // get most Recent Article
        for(var cat : allCat) {
            Article mostRecent = articleRepository.findArticleByCategoryId(cat.getId());
            Integer numArticles = articleRepository.getArticleCountByCategoryId(cat.getId());
            cat.setMostRecentArticle(mostRecent.getJudul());
            cat.setNumArticles(numArticles);
        }
        return allCat;
    }

}
