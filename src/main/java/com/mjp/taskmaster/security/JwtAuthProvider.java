package com.mjp.taskmaster.security;

import io.jsonwebtoken.Claims;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class JwtAuthProvider implements AuthenticationProvider {
    private jwtUtil  jwtUtil;
    @Autowired
    public JwtAuthProvider(jwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }
    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getCredentials();
        Claims claims = jwtUtil.extractAllClaims(token);
        Long userId =  Long.valueOf(claims.getSubject());
        String email = claims.get("email",String.class);
        JwtUserPrincipal principal = new JwtUserPrincipal(userId.toString(),email);
        return new JwtAuthToken(principal, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthToken.class.isAssignableFrom(authentication);
    }
}
