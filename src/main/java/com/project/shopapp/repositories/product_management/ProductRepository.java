package com.project.shopapp.repositories.product_management;

import com.project.shopapp.models.product_management.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.*;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);
}
