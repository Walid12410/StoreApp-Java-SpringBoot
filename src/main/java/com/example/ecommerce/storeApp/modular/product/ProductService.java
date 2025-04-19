package com.example.ecommerce.storeApp.modular.product;


import com.example.ecommerce.storeApp.modular.product.dto.ProductResponseDTO;
import com.example.ecommerce.storeApp.modular.product.mapper.ProductMapper;
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
    private ProductRepository productRepository;


    public ProductPagedResponse<ProductResponseDTO> getAllProduct(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size); // page starts from 0

        Page<Product> products = this.productRepository.findAll(pageable);

        List<ProductResponseDTO> productResponseDTOS = products
                .getContent()
                .stream()
                .map(ProductMapper::toDto)
                .toList();

        return new ProductPagedResponse<>(
                productResponseDTOS,
                products.getNumber(),
                products.getSize(),
                products.getTotalElements(),
                products.getTotalPages(),
                products.isLast()
        );
    }


    public ProductResponseDTO getProduct(Integer id){
        Product existProduct = this.productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        return ProductMapper.toDto(existProduct);
    }

    public ProductResponseDTO updateProduct(Integer id, ProductResponseDTO productResponseDTO){
        Product existProduct = this.productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        if(productResponseDTO.getProductName() != null){
            existProduct.setProductName(productResponseDTO.getProductName());
        }

        if(productResponseDTO.getProductDesc() != null){
            existProduct.setProductDesc(productResponseDTO.getProductDesc());
        }

        if(productResponseDTO.getPrice() != null){
            existProduct.setStock(productResponseDTO.getStock());
        }

        if(productResponseDTO.getSubCategory() != null){

        }

        return null;
    }
}
