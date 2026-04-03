package com.mjp.taskmaster.service;

import com.mjp.taskmaster.dto.CreateUserRequestDTO;
import com.mjp.taskmaster.entity.User;
import com.mjp.taskmaster.exceptions.UserAlreadyExistsException;
import com.mjp.taskmaster.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepo userRepo,PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    public User createUser(CreateUserRequestDTO requestDTODTO) throws UserAlreadyExistsException {
        String email = requestDTODTO.getEmail();
        Optional<User> existingUser = userRepo.findByEmail(email);
        if(existingUser.isPresent()){
            throw new UserAlreadyExistsException("User with email "+ email+"already exists");
        }
        String username = requestDTODTO.getUsername();
        String password = requestDTODTO.getPassword();
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        return userRepo.save(user);
    }
}
