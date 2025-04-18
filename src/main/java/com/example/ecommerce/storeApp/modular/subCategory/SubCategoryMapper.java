package com.example.ecommerce.storeApp.modular.subCategory;

public class SubCategoryMapper {

    public static SubCategoryDTO toDto(SubCategory entity) {

        if(entity == null) return null;

        return SubCategoryDTO.builder()
                .id(entity.getId())
                .subCategoryName(entity.getSubCategoryName())
                .build();
    }

    public static SubCategory toEntity(SubCategoryDTO dto) {

        if (dto == null) return null;

        return SubCategory.builder()
                .id(dto.getId())
                .subCategoryName(dto.getSubCategoryName())
                .build();
    }
}
