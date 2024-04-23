package com.project.shopapp.services.product_management;

import com.project.shopapp.dtos.product_management.SupplierLogoDTO;
import com.project.shopapp.dtos.product_management.SupplierDTO;
import com.project.shopapp.models.product_management.SupplierLogo;
import com.project.shopapp.models.product_management.Supplier;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ISupplierService {
    Supplier createSupplier(SupplierDTO supplierDTO);

    Supplier getSupplierById(long id);

    List<Supplier> getAllSuppliers();

    Supplier updateSupplier(long SupplierId, SupplierDTO supplierDTO);

    void softDeleteSupplier(long id);

    void hardDeleteSupplier(Long id);

    SupplierLogo createSupplierLogo(Long supplierId, MultipartFile file) throws Exception;

    SupplierLogo updateSupplierLogo(Long supplierId, MultipartFile file) throws Exception;
}
