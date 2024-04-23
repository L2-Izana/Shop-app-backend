package com.project.shopapp.repositories.product_management;

import com.project.shopapp.models.product_management.Supplier;
import com.project.shopapp.models.product_management.SupplierLogo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierLogoRepository extends JpaRepository<SupplierLogo, Long> {
    SupplierLogo getSupplierLogoBySupplier(Supplier supplier);
}
