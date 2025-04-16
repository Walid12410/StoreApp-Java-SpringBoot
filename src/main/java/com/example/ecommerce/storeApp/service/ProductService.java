package com.example.ecommerce.storeApp.service;


import com.example.ecommerce.storeApp.model.dto.ProductDTO;
import com.example.ecommerce.storeApp.model.entity.Product;
import com.example.ecommerce.storeApp.model.mapper.ProductMapper;
import com.example.ecommerce.storeApp.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import payload.ProductPagedResponse;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;


    public ProductPagedResponse<ProductDTO> getAllProduct(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size); // page starts from 0

        Page<Product> products = this.productRepo.findAll(pageable);

        List<ProductDTO> productDTOS = products
                .getContent()
                .stream()
                .map(ProductMapper::toDto)
                .toList();

        return new ProductPagedResponse<>(
                productDTOS,
                products.getNumber(),
                products.getSize(),
                products.getTotalElements(),
                products.getTotalPages(),
                products.isLast()
        );
    }


    public ProductDTO getProduct(Integer id){
        Product existProduct = this.productRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        return ProductMapper.toDto(existProduct);
    }

    public ProductDTO updateProduct(Integer id, ProductDTO productDTO){
        Product existProduct = this.productRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        if(productDTO.getProductName() != null){
            existProduct.setProductName(productDTO.getProductName());
        }

        if(productDTO.getProductDesc() != null){
            existProduct.setProductDesc(productDTO.getProductDesc());
        }

        if(productDTO.getPrice() != null){
            existProduct.setStock(productDTO.getStock());
        }

        if(productDTO.getSubCategory() != null){

        }

        return null;
    }
}
