package com.project.shopapp.repositories.order_management;

import com.project.shopapp.models.order_management.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
