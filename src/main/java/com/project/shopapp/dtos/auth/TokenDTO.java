package com.project.shopapp.dtos.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenDTO {
    @NotNull(message = "Token is required")
    @Size(min = 1, max = 255, message = "Token length must be between 1 and 255 characters")
    private String token;

    @NotNull
    @JsonProperty("token_type")
    private String tokenType;

    @NotNull(message = "Expiration Date is required")
    @Future(message = "Expiration Date must be in the future")
    @JsonProperty("expiration_date")
    private LocalDateTime expirationDate;

    private boolean revoked;

    private boolean expired;

    @NotNull(message = "User Id is required")
    @JsonProperty("user_id")
    private Long userId;
}