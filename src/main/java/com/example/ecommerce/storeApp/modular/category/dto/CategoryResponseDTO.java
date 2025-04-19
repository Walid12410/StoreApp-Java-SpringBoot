package com.example.ecommerce.storeApp.modular.category.dto;

import com.example.ecommerce.storeApp.modular.subCategory.dto.SubCategoryResponseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // 💥 This is the key!
public class CategoryResponseDTO {
    private Integer id;
    private String categoryName;
    private List<SubCategoryResponseDTO> subCategories;
}
