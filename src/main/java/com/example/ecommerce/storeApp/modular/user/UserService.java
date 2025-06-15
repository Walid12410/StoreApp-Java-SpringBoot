package com.example.ecommerce.storeApp.modular.user;


import com.example.ecommerce.storeApp.modular.user.dto.*;
import com.example.ecommerce.storeApp.modular.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserMapper userMapper,
                       PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }


    public ResponseDTO createUser(UserCreateDTO userCreateDTO){
        boolean emailExists = this.userRepository
                .findByEmail(userCreateDTO.getEmail())
                .isPresent();

        if (emailExists) {
            throw new ResponseStatusException(HttpStatus.IM_USED, "User already exists");
        }

        User user = userMapper.toEntity(userCreateDTO);
        user = userRepository.save(user);
        return userMapper.loginToDo(user);
    }

    public UserResponseDTO getUserById(Integer id) {
        User existUser = this.userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        return userMapper.toDto(existUser);
    }

    public ResponseDTO loginUser(UserLoginDTO userLoginDTO){
        User user = this.userRepository
                .findByEmail(userLoginDTO.getEmail())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Incorrect Email Or Password"));


        if(!passwordEncoder.matches(userLoginDTO.getPassword(),user.getPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect Email Or Password");
        }

        return userMapper.loginToDo(user);
    }

    public UserResponseDTO updateUser(Integer id, UserUpdateDTO userUpdateDTO){
        User existUser = this.userRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));

        if(userUpdateDTO.getFirstName() != null){
            existUser.setFirstName(userUpdateDTO.getFirstName());
        }

        if(userUpdateDTO.getLastName() != null){
            existUser.setLastName(userUpdateDTO.getLastName());
        }

        if(userUpdateDTO.getCurrentPassword() != null){
            if(userUpdateDTO.getNewPassword() == null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"New password required");
            }

            if (!passwordEncoder.matches(userUpdateDTO.getCurrentPassword(), existUser.getPassword())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Current password is incorrect");
            }
            existUser.setPassword(passwordEncoder.encode(userUpdateDTO.getNewPassword()));

        }

        this.userRepository.save(existUser);// Save changes
        return userMapper.toDto(existUser);
    }


}
