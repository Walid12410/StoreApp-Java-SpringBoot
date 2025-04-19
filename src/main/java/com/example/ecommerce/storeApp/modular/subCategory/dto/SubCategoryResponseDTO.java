package com.example.ecommerce.storeApp.modular.subCategory.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryResponseDTO {
    private Integer id;
    private String subCategoryName;
    private Integer categoryId;
}
