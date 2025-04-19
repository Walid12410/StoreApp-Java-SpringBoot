package com.example.ecommerce.storeApp.modular.category.mapper;

import com.example.ecommerce.storeApp.modular.category.Category;
import com.example.ecommerce.storeApp.modular.category.dto.CategoryCreateDTO;
import com.example.ecommerce.storeApp.modular.category.dto.CategoryResponseDTO;
import com.example.ecommerce.storeApp.modular.subCategory.mapper.SubCategoryMapper;
import com.example.ecommerce.storeApp.modular.subCategory.dto.SubCategoryResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    // Convert Category Entity to CategoryDTO
    public static CategoryResponseDTO toDto(Category entity) {
        List<SubCategoryResponseDTO> subCategoryDTOs = null;
        if (entity.getSubCategories() != null) {
            subCategoryDTOs = entity.getSubCategories().stream()  // Use getSubCategory() instead of getSubCategories()
                    .map(SubCategoryMapper::toDto)
                    .collect(Collectors.toList());
        }

        return CategoryResponseDTO.builder()
                .id(entity.getId())
                .categoryName(entity.getCategoryName())
                .subCategories(subCategoryDTOs)
                .build();
    }

    // Convert Category Entity to CategoryDTO with only category data (without subCategories)
    public static CategoryResponseDTO toDtoOnlyCategory(Category entity) {
        return CategoryResponseDTO.builder()
                .id(entity.getId())
                .categoryName(entity.getCategoryName())
                .subCategories(null)  // or empty list if you prefer: new ArrayList<>()
                .build();
    }

    // Convert CategoryDTO to Category Entity
    public static Category toEntity(CategoryCreateDTO categoryCreateDTO) {
        if (categoryCreateDTO == null) {
            return null;
        }

        Category category = new Category();
        category.setCategoryName(categoryCreateDTO.getCategoryName());

        // Convert SubCategoryDTO list to SubCategory entities if subCategories are present
        if (categoryCreateDTO.getSubCategories() != null) {
            category.setSubCategories(SubCategoryMapper.toEntityList(categoryCreateDTO.getSubCategories()));
        }

        return category;
    }
}
