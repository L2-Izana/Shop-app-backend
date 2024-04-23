package com.project.shopapp.dtos.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenDTO {
    @NotEmpty(message = "Token is required")
    private String token;

    @JsonProperty("token_type")
    private String tokenType;

    @NotNull(message = "Expiration Date is required")
    @JsonProperty("expiration_date")
    private LocalDateTime expirationDate;

    private int revoked;

    private int expired;

    @NotNull(message = "User Id is required")
    @JsonProperty("user_id")
    private Long userId;
}
