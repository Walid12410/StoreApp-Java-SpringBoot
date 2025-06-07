package com.example.ecommerce.storeApp.modular.user;


import com.example.ecommerce.storeApp.modular.user.dto.UserCreateDTO;
import com.example.ecommerce.storeApp.modular.user.dto.UserResponseDTO;
import com.example.ecommerce.storeApp.modular.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public UserResponseDTO createUser(UserCreateDTO userCreateDTO){
        boolean emailExists = this.userRepository
                .findByEmail(userCreateDTO.getEmail())
                .isPresent();

        if (emailExists) {
            throw new ResponseStatusException(HttpStatus.IM_USED, "User already exists");
        }

        User user = userMapper.toEntity(userCreateDTO);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserResponseDTO getUserById(Integer id) {
        User existUser = this.userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        return userMapper.toDto(existUser);
    }
}
