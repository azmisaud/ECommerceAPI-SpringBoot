package com.ECommerceAPI.ECommerceAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Order createOrder(Long userId, Set<Long> productIds) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found with id : " +userId));

        List<Product> productsFound = productRepository.findAllById(productIds);
        if (productsFound.size() != productIds.size()) {          
              throw new RuntimeException("One or more products not found");
        }

        Set<Product> productsToOrder = new HashSet<>(productsFound);

        BigDecimal totalPrice = productsToOrder.stream()
            .map(Product::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = new Order();
        order.setUser(user);
        order.setProducts(productsToOrder);
        order.setTotalAmount(totalPrice);
        order.setOrderDate(LocalDateTime.now());

        return orderRepository.save(order);
    }

    public List<Order> getOrdersForUser (Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found with id : " + userId));
        return orderRepository.findByUser(user);
    }
}
