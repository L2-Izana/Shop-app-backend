package com.project.shopapp.models.product_management;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "is_active", nullable = false)
    private int isActive;
}
