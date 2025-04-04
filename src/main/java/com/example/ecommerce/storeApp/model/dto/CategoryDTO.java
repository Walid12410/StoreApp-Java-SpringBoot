package com.example.ecommerce.storeApp.model.dto;


import com.example.ecommerce.storeApp.model.entity.Category;
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
public class CategoryDTO {
    private Integer id;
    private String categoryName;
    private List<SubCategoryDTO> subCategories;
}
