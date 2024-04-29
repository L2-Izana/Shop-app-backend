package com.project.shopapp.repositories.product_management;

import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.models.product_management.Category;
import com.project.shopapp.models.product_management.Supplier;
import com.project.shopapp.models.product_management.SupplierCategoryAssociation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierCategoryAssociationRepository extends JpaRepository<SupplierCategoryAssociation, Long> {
    SupplierCategoryAssociation findSupplierCategoryAssociationBySupplierAndCategory(Supplier supplier, Category category) throws DataNotFoundException;
    boolean existsSupplierCategoryAssociationBySupplierAndCategory(Supplier supplier, Category category);
}
