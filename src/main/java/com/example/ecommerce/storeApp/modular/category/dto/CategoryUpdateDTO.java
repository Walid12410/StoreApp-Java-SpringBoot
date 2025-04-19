package com.example.ecommerce.storeApp.modular.category.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryUpdateDTO {
    private Integer id;

    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String categoryName;
}
