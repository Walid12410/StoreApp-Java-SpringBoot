package com.example.ecommerce.storeApp.controller;


import com.example.ecommerce.storeApp.model.dto.ProductDTO;
import com.example.ecommerce.storeApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payload.ProductPagedResponse;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<ProductPagedResponse<ProductDTO>> getProducts(
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer size
    ){
        return ResponseEntity.ok(this.productService.getAllProduct(page,size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(
        @PathVariable Integer id
    ){
        return ResponseEntity.ok(this.productService.getProduct(id));
    }


    @PostMapping("/private/{id}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable Integer id,
            @RequestBody ProductDTO productDTO
    ){

    }
}
