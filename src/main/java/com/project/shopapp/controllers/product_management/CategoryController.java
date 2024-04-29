package com.project.shopapp.controllers.product_management;

import com.project.shopapp.dtos.product_management.CategoryDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.exceptions.DuplicateAttributeException;
import com.project.shopapp.models.product_management.Category;
import com.project.shopapp.services.product_management.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.project.shopapp.controllers.helper.DTOValidationController.handleDTOValidationErrors;
import static com.project.shopapp.services.helper.file_management.FileValidationService.validFile;

@RestController
@RequestMapping("${api.prefix}/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategories(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by("id").descending());
        Page<Category> categoryPage = categoryService.getAllCategories(pageRequest);
        List<Category> categories = categoryPage.getContent();
        return ResponseEntity.ok(categories);
    }

    @PostMapping("")
    public ResponseEntity<?> createCategory(
            @Valid @RequestBody CategoryDTO categoryDTO,
            BindingResult result
    ) {
        try {
            ResponseEntity<?> dtoValidationResponse = handleDTOValidationErrors(result);
            if (dtoValidationResponse.getStatusCode().is4xxClientError()) {
                return dtoValidationResponse;
            } else {
                Category updatedCategory = categoryService.createCategory(categoryDTO);
                return ResponseEntity.ok(updatedCategory);
            }
        } catch (DuplicateAttributeException dae) {
            return ResponseEntity.badRequest().body("Category name already exists");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryDTO categoryDTO,
            BindingResult result
    ) {
        try {
            ResponseEntity<?> dtoValidationResponse = handleDTOValidationErrors(result);
            if (dtoValidationResponse.getStatusCode().is4xxClientError()) {
                return dtoValidationResponse;
            } else {
                Category updatedCategory = categoryService.updateCategory(id, categoryDTO);
                return ResponseEntity.ok(updatedCategory);
            }
        } catch (DuplicateAttributeException dae) {
            return ResponseEntity.badRequest().body("Category name already exists");
        } catch (DataNotFoundException dnfe) {
            return ResponseEntity.badRequest().body("Cannot find category with id=" + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok("Deleted category");
        } catch (DataNotFoundException dnfe) {
            return ResponseEntity.badRequest().body("Cannot find category with id=" + id);
        }
    }

    @DeleteMapping("/{id}/hard")
    public ResponseEntity<String> hardDeleteCategory(@PathVariable Long id) {
        categoryService.hardDeleteCategory(id);
        return ResponseEntity.ok("Hard delete category with id=" + id);
    }

    @PostMapping(value = "upload_thumbnail/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadCategoryThumbnail(
            @PathVariable Long id,
            @ModelAttribute("file") MultipartFile file
    ) {
        ResponseEntity<?> fileValidateResponse = validFile(file, "image");
        if (fileValidateResponse.getStatusCode().is4xxClientError()) {
            return fileValidateResponse;
        }
        try {
            categoryService.uploadCategoryThumbnail(id, file);
            return ResponseEntity.ok("Uploaded thumbnail for category, id=" + id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}