package com.example.ecommerce.storeApp.modular.product;


import com.example.ecommerce.storeApp.cloudinary.CloudinaryImage;
import com.example.ecommerce.storeApp.cloudinary.CloudinaryService;
import com.example.ecommerce.storeApp.modular.product.dto.ProductCreateDTO;
import com.example.ecommerce.storeApp.modular.product.dto.ProductResponseDTO;
import com.example.ecommerce.storeApp.modular.product.dto.ProductUpdateDTO;
import com.example.ecommerce.storeApp.modular.product.mapper.ProductMapper;
import com.example.ecommerce.storeApp.modular.subCategory.SubCategory;
import com.example.ecommerce.storeApp.modular.subCategory.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import payload.ProductPagedResponse;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          SubCategoryRepository subCategoryRepository,
                          CloudinaryService cloudinaryService){
        this.productRepository = productRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.cloudinaryService = cloudinaryService;
    }

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

    public ProductResponseDTO updateProduct(Integer id, ProductUpdateDTO productUpdateDTO){
        Product existProduct = this.productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        if(productUpdateDTO.getProductName() != null){
            existProduct.setProductName(productUpdateDTO.getProductName());
        }

        if(productUpdateDTO.getProductDesc() != null){
            existProduct.setProductDesc(productUpdateDTO.getProductDesc());
        }

        if(productUpdateDTO.getPrice() != null){
            existProduct.setStock(productUpdateDTO.getStock());
        }

        if(productUpdateDTO.getStock() != null){
            existProduct.setStock(productUpdateDTO.getStock());
        }

        if(productUpdateDTO.getSubCategoryId() != null){
            SubCategory existsSubCategory = this.subCategoryRepository
                    .findById(productUpdateDTO.getSubCategoryId())
                    .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"SubCategory not found"));

            existProduct.setSubCategory(existsSubCategory);
        }

        Product updateProduct = this.productRepository.save(existProduct);

        return ProductMapper.toDto(updateProduct);
    }


    public ProductResponseDTO createProduct(ProductCreateDTO productCreateDTO, MultipartFile image)  {
        SubCategory existsSubCategory = this.subCategoryRepository
                .findById(productCreateDTO.getSubCategoryId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"SubCategory not found"));

        // Upload image to cloudinary and get the result then save the url and public id in product
        CloudinaryImage imageResult;

        try{
            imageResult = this.cloudinaryService.uploadImage(image);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Failed to upload image");
        }

        Product product = ProductMapper.toEntity(productCreateDTO);
        product.setSubCategory(existsSubCategory);
        product.setImageUrl(imageResult.getImageUrl());
        product.setImageId(imageResult.getPublicId());

        Product savedProduct = this.productRepository.save(product);

        return ProductMapper.toDto(savedProduct);
    }

    public void deleteProduct(Integer id){
        Product exists = this.productRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found"));

        this.productRepository.delete(exists);
    }

}
