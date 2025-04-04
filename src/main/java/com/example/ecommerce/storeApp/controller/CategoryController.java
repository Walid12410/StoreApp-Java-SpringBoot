package com.example.ecommerce.storeApp.controller;

import com.example.ecommerce.storeApp.model.dto.CategoryDTO;
import com.example.ecommerce.storeApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO savedCategoryDTO = categoryService.saveCategory(categoryDTO);

        return ResponseEntity.ok(savedCategoryDTO);
    }


    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategory(){
        List<CategoryDTO> categories = categoryService.getAllCategory();


        if (categories == null || categories.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 No Content if the list is empty or null
        }


        return ResponseEntity.ok(categories);
    }



}
