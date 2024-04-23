package com.project.shopapp.dtos.product_management;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplierDTO {
    private String name;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String address;

    @JsonProperty("is_active")
    private int isActive;
}
