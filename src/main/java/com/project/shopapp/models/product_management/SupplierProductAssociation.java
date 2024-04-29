package com.project.shopapp.models.product_management;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "supplier_product_associations")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierProductAssociation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private float price;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
