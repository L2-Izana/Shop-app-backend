package com.project.shopapp.controllers.product_management;

import com.project.shopapp.dtos.product_management.SupplierDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.exceptions.DuplicateAttributeException;
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

import static com.project.shopapp.controllers.helper.DTOValidationController.handleDTOValidationErrors;
import static com.project.shopapp.services.helper.file_management.FileValidationService.validFile;

@RestController
@RequestMapping("${api.prefix}/suppliers")
@RequiredArgsConstructor
public class SupplierController {
    private final ISupplierService supplierService;

    @GetMapping("/list")
    public ResponseEntity<List<Supplier>> getAllSuppliers(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSupplier(
            @Valid @RequestBody SupplierDTO supplierDTO,
            BindingResult result
    ) {
        try {
            ResponseEntity<?> dtoValidationResponse = handleDTOValidationErrors(result);
            if (dtoValidationResponse.getStatusCode().is4xxClientError()) {
                return dtoValidationResponse;
            } else {
                Supplier updatedSupplier = supplierService.createSupplier(supplierDTO);
                return ResponseEntity.ok(updatedSupplier);
            }
        } catch (DuplicateAttributeException dae) {
            return ResponseEntity.badRequest().body("Supplier name already exists");
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateSupplier(
            @PathVariable Long id,
            @Valid @RequestBody SupplierDTO supplierDTO,
            BindingResult result
    ) {
        try {
            ResponseEntity<?> dtoValidationResponse = handleDTOValidationErrors(result);
            if (dtoValidationResponse.getStatusCode().is4xxClientError()) {
                return dtoValidationResponse;
            } else {
                Supplier updatedSupplier = supplierService.updateSupplier(id, supplierDTO);
                return ResponseEntity.ok(updatedSupplier);
            }
        } catch (DuplicateAttributeException dae) {
            return ResponseEntity.badRequest().body("Supplier name already exists");
        } catch (DataNotFoundException dnfe) {
            return ResponseEntity.badRequest().body("Cannot find supplier with id="+id);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteSupplier(@PathVariable Long id) {
        try {
            supplierService.deleteSupplier(id);
            return ResponseEntity.ok("Deleted supplier");
        } catch (DataNotFoundException dnfe) {
            return ResponseEntity.badRequest().body("Cannot find supplier with id="+id);
        }
    }

    @DeleteMapping("/{id}/hard-delete")
    public ResponseEntity<String> hardDeleteSupplier(@PathVariable Long id) {
        supplierService.hardDeleteSupplier(id);
        return ResponseEntity.ok(String.format("Hard deleted supplier with id=%d", id));
    }

    @PostMapping(value = "/{id}/upload-logo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadSupplierLogo(
            @PathVariable Long id,
            @ModelAttribute("file") MultipartFile file
    ) {
        ResponseEntity<?> fileValidateResponse = validFile(file, "image");
        if (fileValidateResponse.getStatusCode().is4xxClientError()) {
            return fileValidateResponse;
        }
        try {
            supplierService.uploadSupplierLogo(id, file);
            return ResponseEntity.ok("Uploaded logo for supplier, id=" + id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}