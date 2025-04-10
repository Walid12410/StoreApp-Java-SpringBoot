package com.example.ecommerce.storeApp.repository;

import com.example.ecommerce.storeApp.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CatRepo extends JpaRepository<Category, Integer> {

    @Query("SELECT DISTINCT c FROM Category c LEFT JOIN FETCH C.subCategories")
    List<Category> findAllWithSubCategories();
}
