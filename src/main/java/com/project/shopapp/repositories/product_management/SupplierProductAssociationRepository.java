package com.project.shopapp.repositories.product_management;

import com.project.shopapp.models.product_management.SupplierProductAssociation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierProductAssociationRepository extends JpaRepository<SupplierProductAssociation, Long> {
}
