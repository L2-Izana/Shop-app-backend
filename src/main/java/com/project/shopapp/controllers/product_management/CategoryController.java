package com.project.shopapp.controllers.product_management;

import com.project.shopapp.dtos.product_management.CategoryDTO;
import com.project.shopapp.models.product_management.Category;
import com.project.shopapp.services.product_management.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<?> createCategories(
            @Valid @RequestBody CategoryDTO categoryDTO,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok("Insert categories successfully");
    }


    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategories(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryDTO categoryDTO
    ) {
        categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok("Updated category");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Delete category with id: " + id);
    }

    @DeleteMapping("/{id}/hard")
    public ResponseEntity<String> hardDeleteCategory(@PathVariable Long id) {
        categoryService.hardDeleteCategory(id);
        return ResponseEntity.ok("Hard delete category with id: " + id);
    }
}
