package com.pngabo.affrontementapi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Component
public class JwtProvider {
    private final UserDetailsService service;

    public JwtProvider(UserDetailsService service) {
        this.service = service;
    }

    public String createToken(String username, List<String> roles) {
        String token = JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 86_400_000))
                .withClaim("roles", roles)
                .sign(Algorithm.HMAC512("m@_Cl3f_D'3enCryP710n"));

        return "Bearer " + token;
    }

    public String resolveToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }

        return null;
    }

    public boolean validate(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512("m@_Cl3f_D'3enCryP710n"))
                    .build()
                    .verify(token);
            String username = decodedJWT.getSubject();
            Date expiresAt = decodedJWT.getExpiresAt();

            service.loadUserByUsername(username);// Si il ne renvoie rien ca lance une exception

            return username != null && expiresAt != null && expiresAt.after(new Date());
        } catch (JWTVerificationException ex) {
            return false;
        }

    }

    public Authentication getAuth(String token) {
        String username = JWT.decode(token).getSubject();
        UserDetails user = service.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
    }
}
