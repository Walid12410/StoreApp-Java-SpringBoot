package com.example.ecommerce.storeApp.modular.user.mapper;

import com.example.ecommerce.storeApp.config.JwtService;
import com.example.ecommerce.storeApp.modular.user.Role;
import com.example.ecommerce.storeApp.modular.user.User;
import com.example.ecommerce.storeApp.modular.user.dto.ResponseDTO;
import com.example.ecommerce.storeApp.modular.user.dto.UserCreateDTO;
import com.example.ecommerce.storeApp.modular.user.dto.UserResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;


@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserMapper(PasswordEncoder passwordEncoder,
                      JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserResponseDTO toDto(User entity) {
        return UserResponseDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .role(entity.getRole())
                .build();
    }

    public User toEntity(UserCreateDTO userCreateDTO) {
        if (userCreateDTO == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found or invalid");
        }

        return User.builder()
                .firstName(userCreateDTO.getFirstName())
                .lastName(userCreateDTO.getLastName())
                .email(userCreateDTO.getEmail())
                .password(passwordEncoder.encode(userCreateDTO.getPassword()))
                .role(Role.USER)
                .build();
    }

    public ResponseDTO loginToDo(User user){
        if(user == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found or invalid");
        }

        String token = jwtService.generateToken(user);
        return ResponseDTO.builder()
                .token(token)
                .userResponseDTO(toDto(user))
                .build();
    }



}
