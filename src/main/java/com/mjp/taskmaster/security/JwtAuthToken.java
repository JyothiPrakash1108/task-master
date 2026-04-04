package com.mjp.taskmaster.security;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthToken extends AbstractAuthenticationToken {

    private String token;
    private JwtUserPrincipal jwtUserPrincipal;
    public JwtAuthToken(String token){

        super((Collection<? extends GrantedAuthority>) null);
        System.out.println("In jwt token ");
        this.token = token;
        setAuthenticated(false);
    }
    public JwtAuthToken(JwtUserPrincipal principal,
                                  Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        System.out.println("In jwt token after setting auth ");
        this.jwtUserPrincipal = principal;
        this.token = null;
        setAuthenticated(true);
    }
    @Override
    public @Nullable Object getCredentials() {
        return token;
    }

    @Override
    public @Nullable Object getPrincipal() {
        return jwtUserPrincipal;
    }
}
