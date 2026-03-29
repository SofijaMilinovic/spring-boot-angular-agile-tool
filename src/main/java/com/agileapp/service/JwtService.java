package com.agileapp.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private final String SECRET_KEY = "my-super-secret-key-for-jwt-token-123456";

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }


    public String generateToken(String username) {

        return Jwts.builder() //ovde se pokreće JWT builder pattern
                .setSubject(username) // odnosi se na identitet korisnika i kome tooken pripada
                .setIssuedAt(new Date()) //vreme kada je token napravljen
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) //token važi jedan sat
                .signWith(getSigningKey()) //kriptografsko potpisivanje
                .compact(); //ovde se pretvara u finalni string token
    }
}
