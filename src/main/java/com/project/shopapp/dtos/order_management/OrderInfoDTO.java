package com.project.shopapp.dtos.order_management;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderInfoDTO {
    @NotNull(message = "Order Id cannot be null")
    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("fullname")
    private String fullName;

    private String email;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String address;

    private String note;

    @JsonProperty("total_money")
    @Min(value = 0, message = "Total money must be >= 0")
    @Max(value = 10000, message = "Total money must be <= 10000")
    private Float totalMoney;

    @JsonProperty("payment_method")
    private String payment_method;
}
