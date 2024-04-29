package com.project.shopapp.services.product_management;

import com.project.shopapp.dtos.product_management.CategoryDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.exceptions.DuplicateAttributeException;
import com.project.shopapp.exceptions.FileReadException;
import com.project.shopapp.models.product_management.Category;
import com.project.shopapp.models.product_management.CategoryThumbnail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface ICategoryService {
    Category createCategory(CategoryDTO categoryDTO) throws DuplicateAttributeException;

    Category getCategoryById(long id) throws DataNotFoundException;

    Page<Category> getAllCategories(PageRequest pageRequest);

    Category updateCategory(long categoryId, CategoryDTO categoryDTO) throws DataNotFoundException, DuplicateAttributeException;

    void deleteCategory(long id) throws DataNotFoundException;

    void hardDeleteCategory(long id);

    CategoryThumbnail uploadCategoryThumbnail(Long categoryId, MultipartFile file) throws DataNotFoundException, FileReadException;
}
