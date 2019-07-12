package com.arexe.bgames.service;

import com.arexe.bgames.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAllCategories();
    Category findCategoryById(Integer id);
    void saveCategory(Category category);
    void deleteCategoryById(int id);
}
