package com.project.shopapp.services.product_management;

import com.project.shopapp.dtos.product_management.SupplierCategoryAssociationDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.exceptions.DuplicateAttributeException;
import com.project.shopapp.models.product_management.Category;
import com.project.shopapp.models.product_management.Supplier;
import com.project.shopapp.models.product_management.SupplierCategoryAssociation;
import com.project.shopapp.models.product_management.SupplierLogo;

import javax.xml.crypto.Data;
import java.util.List;

public interface ISupplierCategoryAssociationService {
    List<SupplierCategoryAssociation> getAllSupplierCategoryAssociations();

    SupplierCategoryAssociation getSupplierCategoryAssociationById(long id) throws DataNotFoundException;

    SupplierCategoryAssociation getSupplierCategoryAssociationByEntities(Supplier supplier, Category category) throws DataNotFoundException;

    SupplierCategoryAssociation createSupplierCategoryAssociation(SupplierCategoryAssociationDTO supplierCategoryAssociationDTO) throws DataNotFoundException, DuplicateAttributeException;

    SupplierCategoryAssociation updateSupplierCategoryAssociationById(long id, SupplierCategoryAssociationDTO supplierCategoryAssociationDTO) throws DataNotFoundException, DuplicateAttributeException;

    List<Category> getCategoriesBySupplier(Supplier supplier) throws DataNotFoundException;

    List<Supplier> getSuppliersByCategory(Category category) throws DataNotFoundException;
}
