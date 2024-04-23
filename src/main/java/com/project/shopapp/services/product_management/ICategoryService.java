package com.project.shopapp.services.product_management;

import com.project.shopapp.dtos.product_management.CategoryDTO;
import com.project.shopapp.models.product_management.Category;

import java.util.List;

public interface ICategoryService {
    Category createCategory(CategoryDTO categoryDTO);

    Category getCategoryById(long id);

    List<Category> getAllCategories();

    Category updateCategory(long categoryId, CategoryDTO categoryDTO);

    void deleteCategory(long id);

    void hardDeleteCategory(long id);
}
