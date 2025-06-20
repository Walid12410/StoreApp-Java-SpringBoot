package com.example.ecommerce.storeApp.modular.cart.mapper;


import com.example.ecommerce.storeApp.modular.cart.Cart;
import com.example.ecommerce.storeApp.modular.cart.dto.CartCreateDTO;
import com.example.ecommerce.storeApp.modular.cart.dto.CartResponseDTO;
import com.example.ecommerce.storeApp.modular.product.Product;
import com.example.ecommerce.storeApp.modular.user.User;

public class CartMapper {

    public static CartResponseDTO toDto(Cart entity){
        return CartResponseDTO.builder()
                .id(entity.getId())
                .productName(entity.getProduct().getProductName())
                .quantity(entity.getQuantity())
                .totalPrice(entity.getTotalPrice())
                .build();
    }

    public static Cart toEntity(CartCreateDTO cartCreateDTO,
                                User user, Product product){
        if(cartCreateDTO == null){
            return null;
        }

        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setUser(user);
        cart.setQuantity(cartCreateDTO.getQuantity());
        cart.setTotalPrice(cartCreateDTO.getTotalPrice());

        return cart;
    }
}
