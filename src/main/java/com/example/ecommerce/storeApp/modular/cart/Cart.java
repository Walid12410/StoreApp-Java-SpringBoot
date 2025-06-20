package com.example.ecommerce.storeApp.modular.cart;

import com.example.ecommerce.storeApp.modular.product.Product;
import com.example.ecommerce.storeApp.modular.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "cart")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "cart_quantity")
    private Integer quantity;

    @Column(name = "cart_total_price")
    private Double totalPrice;
}
