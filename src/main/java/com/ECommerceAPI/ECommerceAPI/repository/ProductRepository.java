package com.ECommerceAPI.ECommerceAPI.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ECommerceAPI.ECommerceAPI.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
