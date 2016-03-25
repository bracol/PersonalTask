package com.example.waniltonfilho.personaltasks.model.service;

import com.example.waniltonfilho.personaltasks.model.entities.Category;
import com.example.waniltonfilho.personaltasks.model.persistance.category.CategoryRepository;

import java.util.List;

/**
 * Created by Wanilton on 24/03/2016.
 */
public class CategoryService {

    private CategoryService() {
        super();
    }

    public static List<Category> findAll() {
        return CategoryRepository.getAll();
    }

    public static void save(Category category) {
        CategoryRepository.save(category);
    }

    public static void delete(Category selectedCategory) {
        CategoryRepository.delete(selectedCategory.getId());
    }
}
