package com.example.ecommerce.storeApp.repository;

import com.example.ecommerce.storeApp.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    Page<Product> findAll(Pageable pageable);
}
