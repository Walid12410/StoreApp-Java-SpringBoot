package com.example.ecommerce.storeApp.model.dto;


import com.example.ecommerce.storeApp.model.entity.SubCategory;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Integer id;
    private String productName;
    private String productDesc;
    private String price;
    private String stock;
    private String imageId;
    private String imageUrl;
    private SubCategoryDTO subCategory;
}
