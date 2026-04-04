package com.mjp.taskmaster.controller;

import com.mjp.taskmaster.dto.LoginRequestDTO;
import com.mjp.taskmaster.entity.User;
import com.mjp.taskmaster.exceptions.UserNotFoundException;
import com.mjp.taskmaster.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private AuthService authService;
    @Autowired
    public AuthController(AuthenticationManager authenticationManager,AuthService authService){
        this.authenticationManager = authenticationManager;
        this.authService = authService;
    }
    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody LoginRequestDTO requestDTO) throws UserNotFoundException {
        String email = requestDTO.getEmail();
        String password = requestDTO.getPassword();
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email,password)
        );
        if(auth.isAuthenticated()){
            return ResponseEntity.ok(authService.generateJwtToken(email));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
    @GetMapping("/check")
    public String check(){
        return "workign";
    }
}
