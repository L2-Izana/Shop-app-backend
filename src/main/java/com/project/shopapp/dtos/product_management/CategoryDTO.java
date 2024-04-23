package com.project.shopapp.dtos.product_management;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private String name;

    @JsonProperty("is_active")
    private int isActive;
}
