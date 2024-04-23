package com.project.shopapp.dtos.order_management;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {

    @JsonProperty("user_id")
    @NotNull(message = "User id cannot be null")
    private Long userId;

    @NotNull(message = "Order date cannot be null")
    @JsonProperty("order_date")
    private LocalDateTime orderDate;

    @NotNull(message = "Status cannot be null")
    private String status;

    @NotNull(message = "Is active cannot be null")
    @JsonProperty("is_active")
    private int isActive;
}
