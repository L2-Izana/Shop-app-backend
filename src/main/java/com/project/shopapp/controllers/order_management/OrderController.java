package com.project.shopapp.controllers.order_management;

import com.project.shopapp.dtos.order_management.OrderDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/orders")
public class OrderController {
    @PostMapping("")
    public ResponseEntity<?> createOrder(
            @Valid @RequestBody OrderDTO orderDTO,
            BindingResult result
    ) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            return ResponseEntity.ok("createOrder successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<?> getOrders(@Valid @PathVariable("user_id") Long userId) {
        try {
            return ResponseEntity.ok("Get order of a user, user_id=" + userId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(
            @Valid @PathVariable long id,
            @Valid @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok("Update order, id=" + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@Valid @PathVariable Long id) {
        return ResponseEntity.ok("Order deleted successfully.");
    }
}
