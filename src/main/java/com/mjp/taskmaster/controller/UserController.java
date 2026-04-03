package com.mjp.taskmaster.controller;

import com.mjp.taskmaster.dto.CreateUserRequestDTO;
import com.mjp.taskmaster.dto.CreateUserResponseDTO;
import com.mjp.taskmaster.entity.User;
import com.mjp.taskmaster.exceptions.UserAlreadyExistsException;
import com.mjp.taskmaster.mapper.UserMapper;
import com.mjp.taskmaster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping("/register/users")
    public ResponseEntity<?> createuser(@RequestBody CreateUserRequestDTO userRequestDTO) throws UserAlreadyExistsException {
        User createdUser = userService.createUser(userRequestDTO);
        CreateUserResponseDTO responseDTO = UserMapper.toCreateUserResponseDTO(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
