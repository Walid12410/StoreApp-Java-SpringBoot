package com.example.ecommerce.storeApp.model.dto;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryDTO {
    private Integer id;
    private String subCategoryName;
}
