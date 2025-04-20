package com.example.ecommerce.storeApp.modular.product.mapper;

import com.example.ecommerce.storeApp.modular.product.Product;
import com.example.ecommerce.storeApp.modular.product.dto.ProductCreateDTO;
import com.example.ecommerce.storeApp.modular.product.dto.ProductResponseDTO;
import com.example.ecommerce.storeApp.modular.subCategory.mapper.SubCategoryMapper;

public class ProductMapper {

    public static ProductResponseDTO toDto(Product entity){
       return ProductResponseDTO.builder()
               .id(entity.getId())
               .productName(entity.getProductName())
               .productDesc(entity.getProductDesc())
               .imageUrl(entity.getImageUrl())
               .imageId(entity.getImageId())
               .price(entity.getPrice())
               .stock(entity.getStock())
               .subCategory(SubCategoryMapper.toDto(entity.getSubCategory()))
               .build();
    }


    public static Product toEntity(ProductCreateDTO productCreateDTO){
        if(productCreateDTO == null){
            return null;
        }

        Product product = new Product();
        product.setProductName(productCreateDTO.getProductName());
        product.setProductDesc(productCreateDTO.getProductDesc());
        product.setPrice(productCreateDTO.getPrice());
        product.setStock(productCreateDTO.getStock());

        return product;
    }
}
