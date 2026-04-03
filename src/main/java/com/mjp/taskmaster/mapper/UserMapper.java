package com.mjp.taskmaster.mapper;

import com.mjp.taskmaster.dto.CreateUserResponseDTO;
import com.mjp.taskmaster.entity.User;

public class UserMapper {
    public static CreateUserResponseDTO toCreateUserResponseDTO(User user){
        CreateUserResponseDTO responseDTO = new CreateUserResponseDTO();
        responseDTO.setUserId(user.getUserID());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setUsername(user.getUsername());
        return responseDTO;
    }
}
