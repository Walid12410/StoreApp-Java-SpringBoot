package com.example.ecommerce.storeApp.modular.subCategory.mapper;

import com.example.ecommerce.storeApp.modular.subCategory.SubCategory;
import com.example.ecommerce.storeApp.modular.subCategory.dto.SubCategoryCreateDTO;
import com.example.ecommerce.storeApp.modular.subCategory.dto.SubCategoryResponseDTO;
import com.example.ecommerce.storeApp.modular.subCategory.dto.SubCategoryUpdateDTO;

import java.util.List;
import java.util.stream.Collectors;

public class SubCategoryMapper {

    // Convert SubCategory Entity to SubCategoryResponseDTO
    public static SubCategoryResponseDTO toDto(SubCategory entity) {

        if(entity == null){
            return null;
        }

        return SubCategoryResponseDTO.builder()
                .id(entity.getId())
                .subCategoryName(entity.getSubCategoryName())
                .categoryId(entity.getCategory().getId())
                .build();
    }


    // Convert SubCategoryDTO list to SubCategory entities (for create and update)
    public static List<SubCategory> toEntityList(List<SubCategoryResponseDTO> dtos) {
        return dtos.stream()
                .map(dto -> {
                    SubCategory subCategory = new SubCategory();
                    subCategory.setId(dto.getId());
                    subCategory.setSubCategoryName(dto.getSubCategoryName());
                    // Assuming Category is already present or will be set later
                    return subCategory;
                })
                .collect(Collectors.toList());
    }

    // Convert SubCategoryCreateDTO to SubCategory Entity (For Create operation)
    public static SubCategory toEntity(SubCategoryCreateDTO subCategoryCreateDTO) {
        if (subCategoryCreateDTO == null) {
            return null;
        }

        SubCategory subCategory = new SubCategory();
        subCategory.setSubCategoryName(subCategoryCreateDTO.getSubCategoryName());

        return subCategory;
    }

    // Convert SubCategoryUpdateDTO to SubCategory Entity (For Update operation)
    public static SubCategory toEntity(SubCategoryUpdateDTO subCategoryUpdateDTO) {
        if (subCategoryUpdateDTO == null) {
            return null;
        }

        SubCategory subCategory = new SubCategory();
        subCategory.setId(subCategoryUpdateDTO.getId()); // This would be useful for update to keep the same ID
        subCategory.setSubCategoryName(subCategoryUpdateDTO.getSubCategoryName());
        // You can add logic to set the Category as well if necessary

        return subCategory;
    }
}
