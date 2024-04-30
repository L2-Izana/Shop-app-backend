package com.project.shopapp.dtos.auth;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDTO {
    @NotNull(message = "Role name is required")
    @Size(max = 100)
    private String name;
}
