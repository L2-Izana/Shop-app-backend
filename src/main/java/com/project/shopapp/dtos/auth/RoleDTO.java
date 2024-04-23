package com.project.shopapp.dtos.auth;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDTO {
    @NotNull(message="Role name is required")
    private String name;
}
