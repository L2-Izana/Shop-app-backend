package com.project.shopapp.services.product_management;

import com.project.shopapp.dtos.product_management.CategoryDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.exceptions.DuplicateAttributeException;
import com.project.shopapp.exceptions.FileReadException;
import com.project.shopapp.models.product_management.Category;
import com.project.shopapp.models.product_management.CategoryThumbnail;
import com.project.shopapp.repositories.product_management.CategoryRepository;
import com.project.shopapp.repositories.product_management.CategoryThumbnailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.project.shopapp.services.helper.file_management.ImageManipulator.resizeImage;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryThumbnailRepository categoryThumbnailRepository;

    @Override
    public Category createCategory(CategoryDTO categoryDTO) throws DuplicateAttributeException {
        if (categoryRepository.existsByName(categoryDTO.getName()))
            throw new DuplicateAttributeException("This category name already exists");
        Category newCategory = Category.builder()
                .name(categoryDTO.getName())
                .isActive(true)
                .build();
        return categoryRepository.save(newCategory);
    }

    @Override
    public Category getCategoryById(long id) throws DataNotFoundException {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Category not found"));
    }

    @Override
    public Page<Category> getAllCategories(PageRequest pageRequest) {
        return categoryRepository.findAll(pageRequest);
    }

    @Override
    public Category updateCategory(long categoryId,
                                   CategoryDTO categoryDTO) throws DataNotFoundException, DuplicateAttributeException {
        Category existingCategory = getCategoryById(categoryId);
        if (Objects.equals(existingCategory.getName(), categoryDTO.getName())) {
            // Update isActive status
            existingCategory.setActive(true);
            categoryRepository.save(existingCategory);
            return existingCategory;
        }
        if (categoryRepository.existsByName(categoryDTO.getName())) {
            throw new DuplicateAttributeException("Category name already exists");
        }
        existingCategory.setName(categoryDTO.getName());
        existingCategory.setActive(categoryDTO.isActive());
        categoryRepository.save(existingCategory);
        return existingCategory;
    }

    @Override
    public void deleteCategory(long id) throws DataNotFoundException {
        Category existingCategory = getCategoryById(id);
        existingCategory.setActive(false);
        categoryRepository.save(existingCategory);
    }

    public void hardDeleteCategory(long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryThumbnail uploadCategoryThumbnail(Long categoryId, MultipartFile file) throws DataNotFoundException, FileReadException {
        Category existingCategory = getCategoryById(categoryId);
        CategoryThumbnail existingCategoryThumbnail = categoryThumbnailRepository.getCategoryThumbnailByCategory(existingCategory);
        try {
            if (existingCategoryThumbnail == null) {
                CategoryThumbnail categoryThumbnail = new CategoryThumbnail();
                categoryThumbnail.setCategory(existingCategory);
                categoryThumbnail.setImageData(resizeImage(file.getBytes(), "thumbnail"));
                return categoryThumbnailRepository.save(categoryThumbnail);
            } else {
                existingCategoryThumbnail.setImageData(resizeImage(file.getBytes(), "thumbnail"));
                return categoryThumbnailRepository.save(existingCategoryThumbnail);
            }
        } catch (IOException e) {
            throw new FileReadException("Failed to read file");
        }
    }
}
