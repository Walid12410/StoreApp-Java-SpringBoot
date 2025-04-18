package com.example.ecommerce.storeApp.modular.product;

import com.example.ecommerce.storeApp.modular.subCategory.SubCategoryMapper;

public class ProductMapper {

    public static ProductDTO toDto(Product entity){
       return ProductDTO.builder()
               .id(entity.getId())
               .productName(entity.getProductName())
               .productDesc(entity.getProductDesc())
               .imageUrl(entity.getImageUrl())
               .price(entity.getPrice())
               .stock(entity.getStock())
               .subCategory(SubCategoryMapper.toDto(entity.getSubCategory()))
               .build();
    }


    public static Product toEntity(ProductDTO productDTO){
        if(productDTO == null){
            return null;
        }

        Product product = new Product();
        product.setId(productDTO.getId());
        product.setProductName(productDTO.getProductName());
        product.setProductDesc(productDTO.getProductDesc());
        product.setImageId(productDTO.getImageId());
        product.setImageUrl(productDTO.getImageUrl());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setSubCategory(SubCategoryMapper.toEntity(productDTO.getSubCategory()));

        return product;
    }
}
