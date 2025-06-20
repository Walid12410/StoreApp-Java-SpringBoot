package com.example.ecommerce.storeApp.modular.cart.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartCreateDTO {

    @NotNull(message = "productId is required")
    private Integer productId;

    @NotNull(message = "userId is required")
    private Integer userId;

    @NotNull(message = "quantity is required")
    private Integer quantity;

    @NotNull(message = "totalPrice is required")
    private Double totalPrice;
}
