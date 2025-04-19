package com.example.ecommerce.storeApp.modular.category;

import com.example.ecommerce.storeApp.modular.category.dto.CategoryCreateDTO;
import com.example.ecommerce.storeApp.modular.category.dto.CategoryResponseDTO;
import com.example.ecommerce.storeApp.modular.category.dto.CategoryUpdateDTO;
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
    public ResponseEntity<CategoryResponseDTO> createCategoryController(@RequestBody CategoryCreateDTO categoryCreateDTO){
        CategoryResponseDTO savedCategoryResponseDTO = categoryService.saveCategory(categoryCreateDTO);

        return new ResponseEntity<>(savedCategoryResponseDTO,HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategoryController(){
        List<CategoryResponseDTO> categories = categoryService.getAllCategory();


        if (categories == null || categories.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }


        return ResponseEntity.ok(categories);
    }

    @GetMapping("/category-subcategory")
    public ResponseEntity<List<CategoryResponseDTO>> getCategoryWithCategoryController(){
        List<CategoryResponseDTO> categoryWithSubCategory = categoryService.getAllCategoryAndSubCategory();

        if (categoryWithSubCategory == null || categoryWithSubCategory.isEmpty()){
            return ResponseEntity.ok(Collections.emptyList());
        }

        return new ResponseEntity<>(categoryWithSubCategory,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategoryController(
            @PathVariable Integer id,
            @RequestBody CategoryUpdateDTO categoryUpdateDTO){
        CategoryResponseDTO updatedCategory = this.categoryService.updateCategory(id, categoryUpdateDTO);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryController(@PathVariable Integer id){
        this.categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
