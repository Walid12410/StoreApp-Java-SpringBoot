package com.example.ecommerce.storeApp.modular.category;

import com.example.ecommerce.storeApp.modular.category.dto.CategoryCreateDTO;
import com.example.ecommerce.storeApp.modular.category.dto.CategoryResponseDTO;
import com.example.ecommerce.storeApp.modular.category.dto.CategoryUpdateDTO;
import com.example.ecommerce.storeApp.modular.category.mapper.CategoryMapper;
import com.example.ecommerce.storeApp.modular.subCategory.SubCategoryRepository;
import com.example.ecommerce.storeApp.modular.subCategory.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository SubCategoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository){
        this.categoryRepository = categoryRepository;
        this.SubCategoryRepository = subCategoryRepository;
    }

    public List<CategoryResponseDTO> getAllCategoryAndSubCategory(){
        List<Category> categoryWithSubCategory = this.categoryRepository.findAllWithSubCategories();

        if(categoryWithSubCategory.isEmpty()){
            return Collections.emptyList();
        }

        return categoryWithSubCategory.stream()
                .map(CategoryMapper::toDto)
                .collect(Collectors.toList());
    }


    public List<CategoryResponseDTO> getAllCategory() {
        List<Category> categories = this.categoryRepository.findAll();

        if (categories.isEmpty()) {
            return Collections.emptyList(); // Return an empty list instead of null
        }

        return categories.stream()
                .map(CategoryMapper::toDtoOnlyCategory)
                .collect(Collectors.toList());
    }

    public CategoryResponseDTO saveCategory(CategoryCreateDTO categoryCreateDTO){
        // Convert DTO to Entity
        Category category = CategoryMapper.toEntity((categoryCreateDTO));

        // Save category to DB
        Category saveCategory = categoryRepository.save(category);

        // Convert the saved entity back to DTO
        return CategoryMapper.toDtoOnlyCategory(saveCategory);
    }

    public CategoryResponseDTO updateCategory(Integer id, CategoryUpdateDTO categoryUpdateDTO){
        Category existCategory = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with ID: " + id));

        existCategory.setCategoryName(categoryUpdateDTO.getCategoryName());

        Category updateCategory = this.categoryRepository.save(existCategory);

        return CategoryMapper.toDtoOnlyCategory(updateCategory);
    }

    public void deleteCategory(Integer id) {
        Category existCategory = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with ID: " + id));

        List<SubCategory> existSubCategory = this.SubCategoryRepository.findByCategoryId(id);

        if (!existSubCategory.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.IM_USED, "This Category is already used in SubCategory");
        }

        this.categoryRepository.delete(existCategory);
    }

}
