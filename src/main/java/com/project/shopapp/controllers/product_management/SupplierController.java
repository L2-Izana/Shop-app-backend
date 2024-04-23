package com.project.shopapp.controllers.product_management;

import com.project.shopapp.repositories.product_management.SupplierLogoRepository;
import com.project.shopapp.dtos.product_management.SupplierDTO;
import com.project.shopapp.models.product_management.Supplier;
import com.project.shopapp.services.product_management.ISupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.project.shopapp.controllers.FormValidationController.handleFormValidationErrors;
import static com.project.shopapp.services.helper.file_management.FileValidationService.validFile;

@RestController
@RequestMapping("${api.prefix}/suppliers")
@RequiredArgsConstructor
public class SupplierController {
    private final ISupplierService supplierService;

    @GetMapping("")
    public ResponseEntity<List<Supplier>> getAllCategories(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        List<Supplier> categories = supplierService.getAllSuppliers();
        return ResponseEntity.ok(categories);
    }

    @PostMapping("")
    public ResponseEntity<?> createSupplier(
            @Valid @RequestBody SupplierDTO supplierDTO,
            BindingResult result
    ) {
        ResponseEntity<?> formValidationResponse = handleFormValidationErrors(result);
        if (formValidationResponse.getStatusCode().is4xxClientError()) {
            return formValidationResponse;
        } else {
            supplierService.createSupplier(supplierDTO);
            return ResponseEntity.ok("Inserted supplier");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSupplier(
            @PathVariable Long id,
            @Valid @RequestBody SupplierDTO SupplierDTO
    ) {
        supplierService.updateSupplier(id, SupplierDTO);
        return ResponseEntity.ok(String.format("Updated supplier Id=%d", id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable Long id) {
        supplierService.softDeleteSupplier(id);
        return ResponseEntity.ok("Deleted Supplier with id: " + id);
    }

    @DeleteMapping("/{id}/hard")
    public ResponseEntity<String> hardDeleteSupplier(@PathVariable Long id) {
        supplierService.hardDeleteSupplier(id);
        return ResponseEntity.ok(String.format("Hard deleted supplier with id=%d", id));
    }

    @PostMapping(value = "upload_logo/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadSupplierLogo(
            @PathVariable Long id,
            @ModelAttribute("file") MultipartFile file
    ) {
        ResponseEntity<?> fileValidateResponse = validFile(file, "image");
        if (fileValidateResponse.getStatusCode().is4xxClientError()) {
            return fileValidateResponse;
        }
        try {
            supplierService.createSupplierLogo(id, file);
            return ResponseEntity.ok("Uploaded logo for supplier, id=" + id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "update_logo/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateSupplierLogo(
            @PathVariable Long id,
            @ModelAttribute("file") MultipartFile file
    ) {
        ResponseEntity<?> fileValidateResponse = validFile(file, "image");
        if (fileValidateResponse.getStatusCode().is4xxClientError()) {
            return fileValidateResponse;
        }
        try {
            supplierService.updateSupplierLogo(id, file);
            return ResponseEntity.ok("Uploaded logo for supplier, id=" + id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
