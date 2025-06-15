package com.example.ecommerce.storeApp.modular.user.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO {
    private String token;
    private UserResponseDTO userResponseDTO;
}
