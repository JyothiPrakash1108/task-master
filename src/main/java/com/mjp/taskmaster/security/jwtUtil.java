package com.mjp.taskmaster.security;

import com.mjp.taskmaster.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
@Component
public class jwtUtil {
    @Value("${JWT_SECRET}")
    private  String JWT_SECRET;
    @Value("${JWT_EXPIRATION}")
    private  long JWT_EXPIRATION;
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    private  SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
    }
    public  String generateToken(User user){
        return Jwts.builder()
                .setSubject(user.getUserID().toString())
                .claim("email",user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(getSigningKey(),SIGNATURE_ALGORITHM)
                .compact();
    }
    public  Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
