package com.nisum.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * Utility class for generating JWT (JSON Web Tokens). This class provides
 * methods to create tokens used for authentication and authorization,
 * utilizing a secret key and expiration settings defined in application properties.
 *
 * @author avasquez
 */
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private int expirationHours;

    /**
     * Generates a JWT token for the given email.
     *
     * @param email the email address to include in the token's subject
     * @return a signed JWT token as a String
     */
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * expirationHours))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
