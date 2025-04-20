package com.example.ecommerce.storeApp.modular.product.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDTO {

    private Integer id;

    @Size(min = 2, max = 50, message = "Product name must be between 2 and 50 characters")
    private String productName;

    @Size(min = 2, max = 500, message = "Product description must be between 2 and 500 characters")
    private String productDesc;

    @Min(value = 1, message = "Product price must be at least 1")
    @Max(value = 5000, message = "Product price must be at most 5000")
    private Integer price;

    @Min(value = 1, message = "Stock must be at least 1")
    @Max(value = 100, message = "Stock must be at most 100")
    private Integer stock;

    private Integer subCategoryId;
}
