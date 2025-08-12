package com.ECommerceAPI.ECommerceAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ECommerceAPI.ECommerceAPI.model.Order;
import com.ECommerceAPI.ECommerceAPI.model.Product;
import com.ECommerceAPI.ECommerceAPI.model.User;
import com.ECommerceAPI.ECommerceAPI.repository.OrderRepository;
import com.ECommerceAPI.ECommerceAPI.repository.ProductRepository;
import com.ECommerceAPI.ECommerceAPI.repository.UserRepository;

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
