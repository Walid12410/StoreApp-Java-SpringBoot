package com.example.ecommerce.storeApp.modular.cart;


import com.example.ecommerce.storeApp.modular.cart.dto.CartCreateDTO;
import com.example.ecommerce.storeApp.modular.cart.dto.CartResponseDTO;
import com.example.ecommerce.storeApp.modular.cart.dto.CartUpdateDTO;
import com.example.ecommerce.storeApp.modular.cart.mapper.CartMapper;
import com.example.ecommerce.storeApp.modular.product.Product;
import com.example.ecommerce.storeApp.modular.product.ProductRepository;
import com.example.ecommerce.storeApp.modular.user.User;
import com.example.ecommerce.storeApp.modular.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import payload.CartPagedResponse;

import java.util.List;


@Service
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository,
                       UserRepository userRepository,
                       ProductRepository productRepository){
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public CartPagedResponse<CartResponseDTO>
        getUserCart(Integer id, Integer page, Integer limit){

        Pageable pageable = PageRequest.of(page,limit);

        User existUser = this.userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Page<Cart> carts = this.cartRepository.findByUser(existUser, pageable);

        List<CartResponseDTO> cartResponseDTOS = carts
                .getContent()
                .stream()
                .map(CartMapper::toDto)
                .toList();

        return new CartPagedResponse<>(
                cartResponseDTOS,
                carts.getNumber(),
                carts.getSize(),
                carts.getTotalElements(),
                carts.getTotalPages(),
                carts.isLast()
        );
    }

    public CartResponseDTO createCart(CartCreateDTO cartCreateDTO){
        User existUser = this.userRepository.findById(cartCreateDTO.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Product existProduct = this.productRepository.findById(cartCreateDTO.getProductId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        boolean isInCart = this.cartRepository.existsByUserAndProduct(existUser, existProduct);
        if(isInCart){
            throw new ResponseStatusException(HttpStatus.IM_USED,"Product already in cart");
        }

        Cart cart = CartMapper.toEntity(cartCreateDTO,existUser,existProduct);

        Cart savedCart = this.cartRepository.save(cart);

        return CartMapper.toDto(savedCart);
    }

    public CartResponseDTO updateCart(Integer id, CartUpdateDTO cartUpdateDTO){
        Cart cartExist = this.cartRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cart not found"));

        if(cartUpdateDTO.getQuantity() != null) {
            cartExist.setQuantity(cartUpdateDTO.getQuantity());
        }

        if(cartUpdateDTO.getTotalPrice() != null){
            cartExist.setTotalPrice(cartUpdateDTO.getTotalPrice());
        }

        Cart updatedCart = this.cartRepository.save(cartExist);

        return CartMapper.toDto(updatedCart);
    }


    public void deleteCart(Integer id){
        Cart cartExist = this.cartRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cart not found"));

        this.cartRepository.delete(cartExist);
    }

    public void deleteAllCartByUser(Integer id) {
        User existUser = this.userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        this.cartRepository.deleteByUser(existUser);
    }
}
