package com.example.ecommerce.storeApp.modular.cart;

import com.example.ecommerce.storeApp.modular.product.Product;
import com.example.ecommerce.storeApp.modular.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    // Get all cart items for a user
    List<Cart> findByUser(User user);

    // Get paginated cart items for a user
    Page<Cart> findByUser(User user, Pageable pageable);

    // Get cart by user and product
    Optional<Cart> findByUserAndProduct(User user, Product product);

    // Delete cart by user
    void deleteByUser(User user);

    // Check if a product exists in the user's cart
    boolean existsByUserAndProduct(User user, Product product);
}
