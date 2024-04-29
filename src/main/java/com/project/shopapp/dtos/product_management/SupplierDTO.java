package com.project.shopapp.dtos.product_management;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplierDTO {
    @NotNull()
    @Size(max = 100)
    private String name;

    @JsonProperty("phone_number")
    @Size(max = 20)
    private String phoneNumber;

    @Size(max = 100)
    private String address;

    @JsonProperty("is_active")
    private boolean isActive = true;
}
