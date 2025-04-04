package com.example.ecommerce.storeApp.service;

import com.example.ecommerce.storeApp.model.dto.CategoryDTO;
import com.example.ecommerce.storeApp.model.entity.Category;
import com.example.ecommerce.storeApp.model.mapper.CategoryMapper;
import com.example.ecommerce.storeApp.repository.CatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryService {

    @Autowired
    private CatRepo catRepo;

    public List<CategoryDTO> getAllCategoryAndSubCategory(){
        List<Category> categoryWithSubCategory = this.catRepo.findAllWithSubCategories();

        if(categoryWithSubCategory.isEmpty()){
            return null;
        }

        return categoryWithSubCategory.stream()
                .map(CategoryMapper::toDto)
                .collect(Collectors.toList());
    }


    public List<CategoryDTO> getAllCategory() {
        List<Category> categories = this.catRepo.findAll();

        if (categories == null || categories.isEmpty()) {
            return Collections.emptyList(); // Return an empty list instead of null
        }

        return categories.stream()
                .map(CategoryMapper::toDtoOnlyCategory)
                .collect(Collectors.toList());
    }

    public CategoryDTO saveCategory(CategoryDTO categoryDTO){
        // Convert DTO to Entity
        Category category = CategoryMapper.toEntity((categoryDTO));

        // Save category to DB
        Category saveCategory = catRepo.save(category);

        // Convert the saved entity back to DTO
        return CategoryMapper.toDtoOnlyCategory(saveCategory);
    }

}
