package com.example.ecommerce.storeApp.model.mapper;

import com.example.ecommerce.storeApp.model.dto.SubCategoryDTO;
import com.example.ecommerce.storeApp.model.entity.SubCategory;

public class SubCategoryMapper {

    public static SubCategoryDTO toDto(SubCategory entity) {
        return SubCategoryDTO.builder()
                .id(entity.getId())
                .subCategoryName(entity.getSubCategoryName())
                .build();
    }

    public static SubCategory toEntity(SubCategoryDTO dto) {
        return SubCategory.builder()
                .id(dto.getId())
                .subCategoryName(dto.getSubCategoryName())
                .build();
    }
}
