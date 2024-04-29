package com.project.shopapp.models.product_management;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "supplier_product_images")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplier_product_association_id")
    private SupplierProductAssociation supplierProductAssociation;

    @Column(name = "image_data")
    private byte[] imageData;
}
