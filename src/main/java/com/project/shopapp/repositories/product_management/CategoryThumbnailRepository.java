package com.project.shopapp.repositories.product_management;

import com.project.shopapp.models.product_management.Category;
import com.project.shopapp.models.product_management.CategoryThumbnail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryThumbnailRepository extends JpaRepository<CategoryThumbnail, Long> {
    CategoryThumbnail getCategoryThumbnailByCategory(Category category);
}
