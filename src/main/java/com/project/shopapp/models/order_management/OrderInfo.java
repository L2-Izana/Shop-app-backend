package com.project.shopapp.models.order_management;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_infos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "customer_name", length = 100)
    private String customerName;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "phone_number", length = 20, nullable = false)
    private String phoneNumber;

    @Column(name = "address", nullable = false, length = 200)
    private String address;

    @Column(name = "note", length = 100)
    private String note;

    @Column(name = "total_money", nullable = false)
    private Float totalMoney;
}
