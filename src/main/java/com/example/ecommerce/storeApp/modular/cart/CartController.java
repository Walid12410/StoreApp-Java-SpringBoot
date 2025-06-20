package com.example.ecommerce.storeApp.modular.cart;


import com.example.ecommerce.storeApp.modular.cart.dto.CartCreateDTO;
import com.example.ecommerce.storeApp.modular.cart.dto.CartResponseDTO;
import com.example.ecommerce.storeApp.modular.cart.dto.CartUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payload.CartPagedResponse;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<CartPagedResponse<CartResponseDTO>> getUserCart(
            @PathVariable Integer id,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ){
        return ResponseEntity.ok(this.cartService.getUserCart(id,page,size));
    }


    @PostMapping("/")
    public ResponseEntity<CartResponseDTO> createCart(
            @Valid @RequestBody CartCreateDTO cartCreateDTO
            ){
        CartResponseDTO cartResponseDTO = this.cartService.createCart(cartCreateDTO);

        return new ResponseEntity<>(cartResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartResponseDTO> updateCart(
            @PathVariable Integer id,
            @Valid @RequestBody CartUpdateDTO cartUpdateDTO
            ){
        CartResponseDTO cartResponseDTO = this.cartService.updateCart(id,cartUpdateDTO);

        return new ResponseEntity<>(cartResponseDTO,HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Integer id){
        this.cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-all/{id}")
    public ResponseEntity<Void> deleteAllCart(@PathVariable Integer id){
        this.cartService.deleteAllCartByUser(id);
        return ResponseEntity.noContent().build();
    }
}
