package com.project.shopapp.services.product_management;

import com.project.shopapp.dtos.product_management.SupplierCategoryAssociationDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.exceptions.DuplicateAttributeException;
import com.project.shopapp.models.product_management.Category;
import com.project.shopapp.models.product_management.Supplier;
import com.project.shopapp.models.product_management.SupplierCategoryAssociation;
import com.project.shopapp.repositories.product_management.SupplierCategoryAssociationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SupplierCategoryAssociationService implements ISupplierCategoryAssociationService {
    private final SupplierCategoryAssociationRepository supplierCategoryAssociationRepository;
    private final SupplierService supplierService;
    private final CategoryService categoryService;

    @Override
    public List<SupplierCategoryAssociation> getAllSupplierCategoryAssociations() {
        return supplierCategoryAssociationRepository.findAll();
    }

    @Override
    public SupplierCategoryAssociation getSupplierCategoryAssociationById(long id) throws DataNotFoundException {
        return supplierCategoryAssociationRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Cannot find association with id=" + id));
    }

    @Override
    public SupplierCategoryAssociation getSupplierCategoryAssociationByEntities(Supplier supplier, Category category) throws DataNotFoundException {
        return supplierCategoryAssociationRepository.findSupplierCategoryAssociationBySupplierAndCategory(supplier, category);
    }

    @Override
    public SupplierCategoryAssociation createSupplierCategoryAssociation(SupplierCategoryAssociationDTO supplierCategoryAssociationDTO) throws DataNotFoundException, DuplicateAttributeException {
        SupplierCategoryAssociation newSupplierCategoryAssociation = new SupplierCategoryAssociation();
        Supplier supplier = supplierService.getSupplierById(supplierCategoryAssociationDTO.getSupplierId());
        Category category = categoryService.getCategoryById(supplierCategoryAssociationDTO.getCategoryId());
        if (supplierCategoryAssociationRepository.existsSupplierCategoryAssociationBySupplierAndCategory(supplier, category)) {
            throw new DuplicateAttributeException("Association of this (supplier, category) already exists");
        } else {
            newSupplierCategoryAssociation.setCategory(category);
            newSupplierCategoryAssociation.setSupplier(supplier);
            return supplierCategoryAssociationRepository.save(newSupplierCategoryAssociation);
        }
    }

    @Override
    public SupplierCategoryAssociation updateSupplierCategoryAssociationById(long id, SupplierCategoryAssociationDTO supplierCategoryAssociationDTO) throws DataNotFoundException, DuplicateAttributeException {
        return null;
    }

    @Override
    public List<Category> getCategoriesBySupplier(Supplier supplier) throws DataNotFoundException {
        return null;
    }

    @Override
    public List<Supplier> getSuppliersByCategory(Category category) throws DataNotFoundException {
        return null;
    }
}
