package com.example.ecommerce.storeApp.modular.product.mapper;

import com.example.ecommerce.storeApp.modular.product.Product;
import com.example.ecommerce.storeApp.modular.product.dto.ProductResponseDTO;
import com.example.ecommerce.storeApp.modular.subCategory.mapper.SubCategoryMapper;

public class ProductMapper {

    public static ProductResponseDTO toDto(Product entity){
       return ProductResponseDTO.builder()
               .id(entity.getId())
               .productName(entity.getProductName())
               .productDesc(entity.getProductDesc())
               .imageUrl(entity.getImageUrl())
               .price(entity.getPrice())
               .stock(entity.getStock())
               .subCategory(SubCategoryMapper.toDto(entity.getSubCategory()))
               .build();
    }


    public static Product toEntity(ProductResponseDTO productResponseDTO){
        if(productResponseDTO == null){
            return null;
        }

        Product product = new Product();
        product.setId(productResponseDTO.getId());
        product.setProductName(productResponseDTO.getProductName());
        product.setProductDesc(productResponseDTO.getProductDesc());
        product.setImageId(productResponseDTO.getImageId());
        product.setImageUrl(productResponseDTO.getImageUrl());
        product.setPrice(productResponseDTO.getPrice());
        product.setStock(productResponseDTO.getStock());
      //  product.setSubCategory(SubCategoryMapper.toEntity(productDTO.getSubCategory()));

        return product;
    }
}
