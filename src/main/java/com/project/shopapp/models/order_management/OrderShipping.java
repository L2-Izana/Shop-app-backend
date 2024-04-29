package com.project.shopapp.models.order_management;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "order_shipping")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderShipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_info_id", nullable = false)
    private OrderInfo orderInfo;

    @Column(name = "shipping_method", nullable = false)
    @Enumerated(EnumType.STRING)
    private ShippingMethod shippingMethod;

    @Column(name = "shipping_address", nullable = false, length = 200)
    private String shippingAddress;

    @Column(name = "shipping_date", nullable = false)
    private LocalDate shippingDate;

    @Column(name = "tracking_number", nullable = false, unique = true)
    private String trackingNumber;
}
