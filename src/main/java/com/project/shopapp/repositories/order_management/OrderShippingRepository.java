package com.project.shopapp.repositories.order_management;

import com.project.shopapp.models.order_management.OrderShipping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderShippingRepository extends JpaRepository<OrderShipping, Long> {
}
