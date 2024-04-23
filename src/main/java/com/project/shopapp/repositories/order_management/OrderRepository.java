package com.project.shopapp.repositories.order_management;

import com.project.shopapp.models.order_management.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);
}
