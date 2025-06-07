package com.example.ecommerce.storeApp.modular.user.dto;

import com.example.ecommerce.storeApp.modular.user.Role;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserResponseDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
}
