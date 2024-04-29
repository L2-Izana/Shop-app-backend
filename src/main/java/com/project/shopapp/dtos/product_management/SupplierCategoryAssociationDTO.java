package com.project.shopapp.dtos.product_management;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierCategoryAssociationDTO {
    @NotNull(message = "Supplier Id cannot be null")
    @JsonProperty("supplier_id")
    private Long supplierId;

    @NotNull(message = "Category Id cannot be null")
    @JsonProperty("category_id")
    private Long categoryId;
}
