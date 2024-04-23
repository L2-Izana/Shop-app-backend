package com.project.shopapp.dtos.order_management;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderProductDTO {
    @NotNull(message = "Order Id cannot be null")
    @JsonProperty("order_id")
    private Long orderId;

    @NotNull(message = "Product Id cannot be null")
    @JsonProperty("product_id")
    private Long productId;

    @NotNull(message = "Quantity cannot be null")
    private int quantity;

    private Float price;

}
