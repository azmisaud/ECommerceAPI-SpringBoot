package com.ECommerceAPI.ECommerceAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ECommerceAPI.ECommerceAPI.model.Order;
import com.ECommerceAPI.ECommerceAPI.model.User;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    List<Order> findByUser(User user);
}
