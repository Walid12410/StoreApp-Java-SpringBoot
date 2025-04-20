package com.example.ecommerce.storeApp.modular.product.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateDTO {
    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 50, message = "Product name must be between 2 and 50 characters")
    private String productName;

    @NotBlank(message = "Product description is required")
    @Size(min = 2, max = 500, message = "Product description must be between 2 and 500 characters")
    private String productDesc;

    @NotNull(message = "Product price is required")
    @Min(value = 1, message = "Product price must be at least 1")
    @Max(value = 5000, message = "Product price must be at most 5000")
    private Double price;

    @NotNull(message = "Stock is required")
    @Min(value = 1, message = "Stock must be at least 1")
    @Max(value = 100, message = "Stock must be at most 100")
    private Integer stock;

    @NotNull(message = "SubCategory is required")
    private Integer subCategoryId;
}
