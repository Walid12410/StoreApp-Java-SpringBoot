package com.example.ecommerce.storeApp.repository;

import com.example.ecommerce.storeApp.model.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCatRepo extends JpaRepository<SubCategory, Integer> {
}
