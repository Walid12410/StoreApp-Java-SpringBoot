package com.example.ecommerce.storeApp.modular.category.dto;

import com.example.ecommerce.storeApp.modular.subCategory.dto.SubCategoryResponseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreateDTO {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String categoryName;

    private List<SubCategoryResponseDTO> subCategories; // Optional, can be empty

}
