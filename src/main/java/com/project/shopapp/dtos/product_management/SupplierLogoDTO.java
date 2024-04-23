package com.project.shopapp.dtos.product_management;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplierLogoDTO {
    @JsonProperty("supplier_id")
    private Long supplierId;

    @JsonProperty("image_url")
    private String imageUrl;
}
