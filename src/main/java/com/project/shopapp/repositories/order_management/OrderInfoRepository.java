package com.project.shopapp.repositories.order_management;

import com.project.shopapp.models.order_management.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long> {
}
