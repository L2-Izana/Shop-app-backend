package com.project.shopapp.services.product_management;

import com.project.shopapp.dtos.product_management.CategoryDTO;
import com.project.shopapp.models.product_management.Category;
import com.project.shopapp.repositories.product_management.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        if (categoryRepository.existsByName(categoryDTO.getName())) {
            return null;
        }
        Category newCategory = Category.builder()
                .name(categoryDTO.getName())
                .isActive(1)
                .build();
        return categoryRepository.save(newCategory);
    }

    @Override
    public Category getCategoryById(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(long categoryId,
                                   CategoryDTO categoryDTO) {
        Category existingCategory = getCategoryById(categoryId);
        existingCategory.setName((categoryDTO.getName() != null) ? categoryDTO.getName() : existingCategory.getName());
        existingCategory.setIsActive(categoryDTO.getIsActive() == 1 ? 1 : existingCategory.getIsActive());
        categoryRepository.save(existingCategory);
        return existingCategory;
    }

    @Override
    public void deleteCategory(long id) {
        Category existingCategory = getCategoryById(id);
        existingCategory.setIsActive(0);
        categoryRepository.save(existingCategory);
    }

    public void hardDeleteCategory(long id) {
        categoryRepository.deleteById(id);
    }
}
