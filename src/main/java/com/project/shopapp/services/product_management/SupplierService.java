package com.project.shopapp.services.product_management;

import com.project.shopapp.dtos.product_management.SupplierDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.exceptions.DuplicateAttributeException;
import com.project.shopapp.exceptions.FileReadException;
import com.project.shopapp.models.product_management.Supplier;
import com.project.shopapp.models.product_management.SupplierLogo;
import com.project.shopapp.repositories.product_management.SupplierLogoRepository;
import com.project.shopapp.repositories.product_management.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.project.shopapp.services.helper.file_management.ImageManipulator.resizeImage;

@Service
@RequiredArgsConstructor
public class SupplierService implements ISupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierLogoRepository supplierLogoRepository;

    @Override
    public Supplier createSupplier(SupplierDTO supplierDTO) throws DuplicateAttributeException {
        if (supplierRepository.existsByName(supplierDTO.getName())) {
            throw new DuplicateAttributeException("This supplier name already exists");
        }
        Supplier newSupplier = Supplier.builder()
                .name(supplierDTO.getName())
                .phoneNumber(supplierDTO.getPhoneNumber())
                .address(supplierDTO.getAddress())
                .isActive(true)
                .build();
        return supplierRepository.save(newSupplier);
    }

    @Override
    public Supplier getSupplierById(long id) throws DataNotFoundException {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Supplier not found"));
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier updateSupplier(long supplierId,
                                   SupplierDTO supplierDTO) throws DataNotFoundException, DuplicateAttributeException {
        Supplier existingSupplier = getSupplierById(supplierId);
        if (Objects.equals(existingSupplier.getName(), supplierDTO.getName())) {
            // Update information
            existingSupplier.setPhoneNumber(supplierDTO.getPhoneNumber());
            existingSupplier.setAddress(supplierDTO.getAddress());
            existingSupplier.setActive(supplierDTO.isActive());
            supplierRepository.save(existingSupplier);
            return existingSupplier;
        }
        if (supplierRepository.existsByName(supplierDTO.getName())) {
            throw new DuplicateAttributeException("Supplier name already exists");
        }
        existingSupplier.setName(supplierDTO.getName());
        existingSupplier.setPhoneNumber(supplierDTO.getPhoneNumber());
        existingSupplier.setAddress(supplierDTO.getAddress());
        existingSupplier.setActive(supplierDTO.isActive());
        supplierRepository.save(existingSupplier);
        return existingSupplier;
    }

    @Override
    public void deleteSupplier(long id) throws DataNotFoundException {
        Supplier existingSupplier = getSupplierById(id);
        existingSupplier.setActive(false);
        supplierRepository.save(existingSupplier);
    }

    @Override
    public void hardDeleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }

    @Override
    public SupplierLogo uploadSupplierLogo(Long supplierId, MultipartFile file) throws DataNotFoundException, FileReadException {
        Supplier existingSupplier = getSupplierById(supplierId);
        SupplierLogo existingSupplierLogo = supplierLogoRepository.getSupplierLogoBySupplier(existingSupplier);
        try {
            if (existingSupplierLogo == null) {
                SupplierLogo newSupplierLogo = new SupplierLogo();
                newSupplierLogo.setSupplier(existingSupplier);
                newSupplierLogo.setImageData(resizeImage(file.getBytes(), "thumbnail"));
                return supplierLogoRepository.save(newSupplierLogo);
            } else {
                existingSupplierLogo.setImageData(resizeImage(file.getBytes(), "thumbnail"));
                return supplierLogoRepository.save(existingSupplierLogo);
            }
        } catch (IOException e) {
            throw new FileReadException("Failed to read file");
        }
    }
}
