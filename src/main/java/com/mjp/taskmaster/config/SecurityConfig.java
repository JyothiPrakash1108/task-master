package com.mjp.taskmaster.config;

import com.mjp.taskmaster.security.JwtAuthFilter;
import com.mjp.taskmaster.security.JwtAuthProvider;
import com.mjp.taskmaster.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private PasswordEncoder passwordEncoder;
    private AuthService authService;
    private JwtAuthProvider authProvider;

    @Autowired
    public SecurityConfig(JwtAuthProvider authProvider,PasswordEncoder passwordEncoder,AuthService authService){
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
        this.authProvider = authProvider;
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(AuthService authService,
                                                               PasswordEncoder passwordEncoder) {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(authService);
        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }
    @Bean
    public AuthenticationManager authenticationManager(
            DaoAuthenticationProvider daoProvider,
            JwtAuthProvider jwtProvider) {

        return new ProviderManager(
                List.of(
                        daoProvider,     // handles login
                        jwtProvider      // handles JWT
                )
        );
    }
    @Bean
    public JwtAuthFilter jwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        return new JwtAuthFilter(authenticationManager);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,JwtAuthFilter jwtFilter){
        http
                .csrf( csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin( form -> form.disable())
                .httpBasic( basic -> basic.disable())
                .authorizeHttpRequests( auth -> auth
                        .requestMatchers("/api/register/users","/api/auth/login").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
