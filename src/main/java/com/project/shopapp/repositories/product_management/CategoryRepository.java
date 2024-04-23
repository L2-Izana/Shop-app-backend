package com.project.shopapp.repositories.product_management;

import com.project.shopapp.models.product_management.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
}
