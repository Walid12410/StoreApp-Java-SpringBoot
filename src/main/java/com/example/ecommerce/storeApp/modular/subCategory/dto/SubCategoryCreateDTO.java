package com.example.ecommerce.storeApp.modular.subCategory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryCreateDTO {

    @NotBlank(message = "subcategory Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String subCategoryName;

    @NotNull(message = "Category id is required")
    private Integer categoryId;

}
