package com.project.shopapp.dtos.order_management;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderShippingDTO {
    @NotNull(message = "Order Info Id cannot be null")
    @JsonProperty("order_info_id")
    private Long orderInfoId;

    @JsonProperty("shipping_method")
    private String shippingMethod;

    @JsonProperty("shipping_address")
    private String shippingAddress;

    @JsonProperty("shipping_date")
    private Date shippingDate;

    @JsonProperty("tracking_number")
    private String trackingNumber;
}
