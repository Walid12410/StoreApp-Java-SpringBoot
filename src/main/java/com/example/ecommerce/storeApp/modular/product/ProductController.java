package com.example.ecommerce.storeApp.modular.product;


import com.example.ecommerce.storeApp.modular.product.dto.ProductCreateDTO;
import com.example.ecommerce.storeApp.modular.product.dto.ProductResponseDTO;
import com.example.ecommerce.storeApp.modular.product.dto.ProductUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import payload.ProductPagedResponse;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/public")
    public ResponseEntity<ProductPagedResponse<ProductResponseDTO>> getProductsController(
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer size
    ){
        return ResponseEntity.ok(this.productService.getAllProduct(page,size));
    }

    @GetMapping("/public/{id}")
    public ResponseEntity<ProductResponseDTO> getProductController(
        @PathVariable Integer id
    ){
        ProductResponseDTO product = this.productService.getProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    @PutMapping("/private/{id}")
    public ResponseEntity<ProductResponseDTO> updateProductController(
            @PathVariable Integer id,
            @Valid @RequestBody ProductUpdateDTO productUpdateDTO
    ){
        ProductResponseDTO updatedProduct = this.productService.updateProduct(id,productUpdateDTO);
        return new ResponseEntity<>(updatedProduct,HttpStatus.OK);
    }

    @PostMapping("/private")
    public ResponseEntity<ProductResponseDTO> createProductController(
            @Valid @RequestBody ProductCreateDTO productCreateDTO,
            @RequestParam("image") MultipartFile image
    ){
        ProductResponseDTO createProduct = this.productService.createProduct(productCreateDTO, image);
        return new ResponseEntity<>(createProduct,HttpStatus.CREATED);
    }

    @DeleteMapping("/private/{id}")
    public ResponseEntity<Void> deleteProductController(@PathVariable Integer id){
        this.productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
