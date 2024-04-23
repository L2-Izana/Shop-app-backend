package com.project.shopapp.services.product_management;

import com.project.shopapp.dtos.product_management.SupplierDTO;
import com.project.shopapp.models.product_management.Supplier;
import com.project.shopapp.models.product_management.SupplierLogo;
import com.project.shopapp.repositories.product_management.SupplierLogoRepository;
import com.project.shopapp.repositories.product_management.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.project.shopapp.services.helper.file_management.FileStorageService.storeFile;

@Service
@RequiredArgsConstructor
public class SupplierService implements ISupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierLogoRepository supplierLogoRepository;

    @Override
    public Supplier createSupplier(SupplierDTO supplierDTO) {
        if (supplierRepository.existsByName(supplierDTO.getName())) {
            return null;
        }
        Supplier newSupplier = Supplier.builder()
                .name(supplierDTO.getName())
                .phoneNumber(supplierDTO.getPhoneNumber())
                .address(supplierDTO.getAddress())
                .isActive(1)
                .build();
        return supplierRepository.save(newSupplier);
    }

    @Override
    public Supplier getSupplierById(long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier updateSupplier(long supplierId,
                                   SupplierDTO supplierDTO) {
        Supplier existingSupplier = getSupplierById(supplierId);
        existingSupplier.setName((supplierDTO.getName() != null) ? supplierDTO.getName() : existingSupplier.getName());
        existingSupplier.setPhoneNumber((supplierDTO.getPhoneNumber() != null) ? supplierDTO.getPhoneNumber() : existingSupplier.getPhoneNumber());
        existingSupplier.setAddress((supplierDTO.getAddress() != null) ? supplierDTO.getAddress() : existingSupplier.getAddress());
        existingSupplier.setIsActive(supplierDTO.getIsActive() == 1 ? 1 : existingSupplier.getIsActive());
        supplierRepository.save(existingSupplier);
        return existingSupplier;
    }

    @Override
    public void softDeleteSupplier(long id) {
        Supplier existingSupplier = getSupplierById(id);
        existingSupplier.setIsActive(0);
        supplierRepository.save(existingSupplier);
    }

    @Override
    public void hardDeleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }

    @Override
    public SupplierLogo createSupplierLogo(Long supplierId, MultipartFile file) throws Exception {
        Supplier existingSupplier = getSupplierById(supplierId);

        storeFile(file);
        SupplierLogo supplierLogo = new SupplierLogo();
        supplierLogo.setSupplier(existingSupplier);
        supplierLogo.setData(file.getBytes());
        return supplierLogoRepository.save(supplierLogo);
    }

    @Override
    public SupplierLogo updateSupplierLogo(Long supplierId, MultipartFile file) throws Exception {
        Supplier existingSupplier = getSupplierById(supplierId);
        SupplierLogo existingSupplierLogo = supplierLogoRepository.getSupplierLogoBySupplier(existingSupplier);
        existingSupplierLogo.setData(file.getBytes());
        return supplierLogoRepository.save(existingSupplierLogo);
    }
}
