package com.project.shopapp.models.product_management;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "is_active", nullable = false)
    private int isActive;
}
