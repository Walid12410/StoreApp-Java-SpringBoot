package com.example.ecommerce.storeApp.modular.product;


import com.example.ecommerce.storeApp.modular.product.dto.ProductResponseDTO;
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
    public ResponseEntity<ProductPagedResponse<ProductResponseDTO>> getProducts(
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer size
    ){
        return ResponseEntity.ok(this.productService.getAllProduct(page,size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(
        @PathVariable Integer id
    ){
        return ResponseEntity.ok(this.productService.getProduct(id));
    }


    @PostMapping("/private/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable Integer id,
            @RequestBody ProductResponseDTO productResponseDTO
    ){
        return null;
    }
}
