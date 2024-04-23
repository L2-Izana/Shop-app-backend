package com.project.shopapp.repositories.product_management;

import com.project.shopapp.models.product_management.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    boolean existsByName(String name);
}
