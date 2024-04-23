package com.project.shopapp.models.product_management;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "supplier_logos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplierLogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Lob
    @Column(name = "data")
    private byte[] data;
}
