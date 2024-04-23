package com.project.shopapp.controllers.product_management;

import com.project.shopapp.dtos.product_management.ProductDTO;
import com.project.shopapp.dtos.product_management.ProductImageDTO;
import com.project.shopapp.models.product_management.Product;
import com.project.shopapp.models.product_management.ProductImage;
import com.project.shopapp.services.product_management.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static com.project.shopapp.controllers.FormValidationController.handleFormValidationErrors;
import static com.project.shopapp.services.helper.file_management.FileStorageService.storeFile;
import static com.project.shopapp.services.helper.file_management.FileValidationService.validFile;

@RestController
@RequestMapping("${api.prefix}/products")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService productService;


    @GetMapping("")
    public ResponseEntity<String> getProducts(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        return ResponseEntity.ok("getProducts here");
    }

    @PostMapping("")
    public ResponseEntity<?> createProduct(
            @Valid @RequestBody ProductDTO productDTO,
            BindingResult result
    ) {
        try {
            ResponseEntity<?> formValidationResponse = handleFormValidationErrors(result);
            if (formValidationResponse.getStatusCode().is4xxClientError()) {
                return formValidationResponse;
            } else {
                Product newProduct = productService.createProduct(productDTO);
                return ResponseEntity.ok(newProduct);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getProductById(
            @PathVariable("id") String productId
    ) {
        return ResponseEntity.ok("Product with ID: " + productId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id) {
        return ResponseEntity.ok(String.format("Product with id = %d deleted successfully", id));
    }
//
//    @PostMapping(value = "uploads/{id}",
//            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<?> uploadImages(
//            @PathVariable("id") Long productId,
//            @ModelAttribute("files") List<MultipartFile> files
//    ) {
//        try {
//            Product existingProduct = productService.getProductById(productId);
//            files = files == null ? new ArrayList<MultipartFile>() : files;
//            List<ProductImage> productImages = new ArrayList<>();
//            for (MultipartFile file : files) {
//                ResponseEntity<?> fileValidateResponse = validFile(file, "image");
//                if (fileValidateResponse.getStatusCode().is4xxClientError()) {
//                    return fileValidateResponse;
//                }
//                String filename = storeFile(file);
//                ProductImage productImage = productService.createProductImage(
//                        existingProduct.getId(),
//                        ProductImageDTO.builder()
//                                .imageUrl(filename)
//                                .build()
//                );
//                productImages.add(productImage);
//            }
//            return ResponseEntity.ok().body(productImages);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
}