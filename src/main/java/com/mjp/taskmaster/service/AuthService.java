package com.mjp.taskmaster.service;

import com.mjp.taskmaster.entity.User;
import com.mjp.taskmaster.exceptions.UserNotFoundException;
import com.mjp.taskmaster.repo.UserRepo;
import com.mjp.taskmaster.security.jwtUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {
    private UserRepo userRepo;
    private jwtUtil jwtUtil;
    private PasswordEncoder passwordEncoder;
    public AuthService(UserRepo userRepo,PasswordEncoder passwordEncoder,jwtUtil jwtUtil){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> existingUser = userRepo.findByEmail(email);
        if(existingUser.isEmpty()){
            throw new UsernameNotFoundException("user with email "+email+" not found");
        }
        return org.springframework.security.core.userdetails.User.withUsername(email)
                .password(existingUser.get().getPassword())
                .build();
    }
    public String generateJwtToken(String email) throws UserNotFoundException {
        Optional<User> userOpt = userRepo.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("User with email " + email + " not found");
        }
        return jwtUtil.generateToken(userOpt.get());
    }
}
