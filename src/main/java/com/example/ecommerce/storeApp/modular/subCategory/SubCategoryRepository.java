package com.example.ecommerce.storeApp.modular.subCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {

    @Query("SELECT s FROM SubCategory s WHERE s.category.id = :categoryId")
    List<SubCategory> findByCategoryId(@Param("categoryId") Integer categoryId);

}
