package com.project.shopapp.dtos.product_management;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    @NotNull(message = "Title is required")
    @Size(min = 3, max = 200, message = "Product name must be between 3 and 200 characters")
    private String name;

    @Min(value = 0, message = "Price must be >= 0")
    @Max(value = 10000, message = "Price must be <= 10,000")
    private Float price;

    @NotNull(message = "Stock quantity must not be null")
    @JsonProperty("stock_quantity")
    private int stock_quantity;

    private String thumbnail;

    private String description;

    @NotNull(message = "created_at cannot be null")
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("category_id")
    private Long categoryId;

    @JsonProperty("supplier_id")
    private Long supplierId;

    private List<MultipartFile> files;
}
