package com.example.ecommerce.storeApp.modular.user;


import com.example.ecommerce.storeApp.modular.user.dto.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(
            @Valid @RequestBody UserCreateDTO userCreateDTO
            ){
        ResponseDTO userResponseDTO = this.userService.createUser(userCreateDTO);

        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(
            @Valid @RequestBody UserLoginDTO userLoginDTO
            ){
        ResponseDTO loginResponse = this.userService.loginUser(userLoginDTO);

        return new ResponseEntity<>(loginResponse,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserController(
            @PathVariable Integer id
    ){
        UserResponseDTO userResponseDTO = this.userService.getUserById(id);

        return new ResponseEntity<>(userResponseDTO,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUserController(
            @PathVariable Integer id,
            @Valid @RequestBody UserUpdateDTO userUpdateDTO
            ){
        UserResponseDTO updatedUser = this.userService.updateUser(id,userUpdateDTO);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
