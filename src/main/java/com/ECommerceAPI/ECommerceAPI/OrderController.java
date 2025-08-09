package com.ECommerceAPI.ECommerceAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    public record OrderRequest(Set<Long> productIds) {}

    @PostMapping("/{userId}")
    public ResponseEntity<Order> createOrder(@PathVariable Long userId, @RequestBody OrderRequest request) {
        try {
            Order newOrder = orderService.createOrder(userId, request.productIds());
            return ResponseEntity.ok(newOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> getOrdersForUser(@PathVariable Long userId) {
        try {
            List<Order> orders = orderService.getOrdersForUser(userId);

            orders.forEach(order -> {
                if (order.getUser() != null) {
                    order.getUser().setPassword(null); // Hide password in response
                }
            });
            
            return ResponseEntity.ok(orders);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
