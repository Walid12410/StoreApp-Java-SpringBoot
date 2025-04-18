package com.example.ecommerce.storeApp.modular.category;

import com.example.ecommerce.storeApp.modular.subCategory.SubCategoryDTO;
import com.example.ecommerce.storeApp.modular.subCategory.SubCategoryMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static CategoryDTO toDto(Category entity) {
        List<SubCategoryDTO> subCategoryDTOs = null;
        if (entity.getSubCategories() != null) {
            subCategoryDTOs = entity.getSubCategories().stream()
                    .map(SubCategoryMapper::toDto)
                    .collect(Collectors.toList());
        }

        return CategoryDTO.builder()
                .id(entity.getId())
                .categoryName(entity.getCategoryName())
                .subCategories(subCategoryDTOs)
                .build();
    }

    public static CategoryDTO toDtoOnlyCategory(Category entity) {
        return CategoryDTO.builder()
                .id(entity.getId())
                .categoryName(entity.getCategoryName())
                .subCategories(null) // or empty list if you prefer: new ArrayList<>()
                .build();
    }

    // Convert CategoryDTO to Category Entity
    public static Category toEntity(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            return null;
        }

        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setCategoryName(categoryDTO.getCategoryName());

        // Optionally, if you want to handle subCategories (if present in the DTO)
        // category.setSubCategories(SubCategoryMapper.toEntityList(categoryDTO.getSubCategories()));

        return category;
    }

}

