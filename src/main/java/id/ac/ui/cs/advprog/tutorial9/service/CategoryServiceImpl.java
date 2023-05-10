package id.ac.ui.cs.advprog.tutorial9.service;

import id.ac.ui.cs.advprog.tutorial9.model.Article;
import id.ac.ui.cs.advprog.tutorial9.model.Category;
import id.ac.ui.cs.advprog.tutorial9.repository.CategoryRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{

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

            Article mostRecent = cat.getArticles().get(0);
            for(var art : cat.getArticles()) {
                if(art.getCreatedAt().getTime() > mostRecent.getCreatedAt().getTime()) {
                    mostRecent = art;
                }
            }

            cat.setMostRecentArticle(mostRecent.getJudul());
            cat.setNumArticles(cat.getArticles().size());
        }
        return allCat;
    }

}
