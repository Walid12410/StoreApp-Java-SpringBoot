package com.example.ecommerce.storeApp.controller;

import com.example.ecommerce.storeApp.model.dto.CategoryDTO;
import com.example.ecommerce.storeApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping
    public ResponseEntity<CategoryDTO> createCategoryController(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO savedCategoryDTO = categoryService.saveCategory(categoryDTO);

        return new ResponseEntity<>(savedCategoryDTO,HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategoryController(){
        List<CategoryDTO> categories = categoryService.getAllCategory();


        if (categories == null || categories.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }


        return ResponseEntity.ok(categories);
    }

    @GetMapping("/category-subcategory")
    public ResponseEntity<List<CategoryDTO>> getCategoryWithCategoryController(){
        List<CategoryDTO> categoryWithSubCategory = categoryService.getAllCategoryAndSubCategory();

        if (categoryWithSubCategory == null || categoryWithSubCategory.isEmpty()){
            return ResponseEntity.ok(Collections.emptyList());
        }

        return new ResponseEntity<>(categoryWithSubCategory,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategoryController(
            @PathVariable Integer id,
            @RequestBody CategoryDTO categoryDTO){
        CategoryDTO updatedCategory = this.categoryService.updateCategory(id,categoryDTO);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryController(@PathVariable Integer id){
        this.categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
