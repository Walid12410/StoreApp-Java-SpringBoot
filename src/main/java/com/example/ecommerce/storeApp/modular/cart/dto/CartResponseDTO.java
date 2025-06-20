package com.example.ecommerce.storeApp.modular.cart.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseDTO {
    private Integer id;
    private String productName;
    private Integer quantity;
    private Double totalPrice;
}
