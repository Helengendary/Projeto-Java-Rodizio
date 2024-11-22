package com.example.demo.impl;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import com.example.demo.dto.Token;
import com.example.demo.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtImpl implements JwtService<Token> {
    private final String SECRET_KEY = "ouqebfdouiebfouqewfnuoqewnhfouewnfouewnhporqueissotaacontecendo";
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hora

    @Override
    public String get(Token token) {
        var claims = new HashMap<String, Object>();
        
        claims.put("id", token.getId());
        claims.put("email", token.getEmail());

        return get(claims);
    }

    @Override
    public Token validate(String jwt) {
        try
        {
            var map = validateJwt(jwt);

            Token token = new Token();
            token.setId(Long.valueOf(map.get("id").toString()));
            token.setEmail(map.get("email").toString());

            return token;
        }
        catch (NumberFormatException ex)
        {
            return null;
        }
    }

    private String get(Map<String, Object> customClaims) {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
            .claims()
                .add(customClaims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .and()
            .signWith(key)
            .compact();
    }

    private Map<String, Object> validateJwt(String jwt) {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        Claims claims = Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(jwt)
            .getPayload();
        
        return new HashMap<>(claims);
    }
}
