package com.project.shopapp.dtos.user_management;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    @NotNull(message = "Phone number is required")
    @JsonProperty("phone_number")
    private String phoneNumber;

    @NotNull(message = "Password is required")
    private String password;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("is_active")
    private int isActive;

    @NotNull(message = "Role Id is required")
    @JsonProperty("role_id")
    private Long roleId;
}
