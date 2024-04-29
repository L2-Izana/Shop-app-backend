package com.project.shopapp.services.product_management;

import com.project.shopapp.dtos.product_management.SupplierDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.exceptions.DuplicateAttributeException;
import com.project.shopapp.exceptions.FileReadException;
import com.project.shopapp.models.product_management.SupplierLogo;
import com.project.shopapp.models.product_management.Supplier;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ISupplierService {
    Supplier createSupplier(SupplierDTO supplierDTO) throws DuplicateAttributeException;

    Supplier getSupplierById(long id) throws DataNotFoundException;

    List<Supplier> getAllSuppliers();

    Supplier updateSupplier(long SupplierId, SupplierDTO supplierDTO) throws DataNotFoundException, DuplicateAttributeException;

    void deleteSupplier(long id) throws DataNotFoundException;

    void hardDeleteSupplier(Long id);

    SupplierLogo uploadSupplierLogo(Long supplierId, MultipartFile file) throws DataNotFoundException, FileReadException;
}
