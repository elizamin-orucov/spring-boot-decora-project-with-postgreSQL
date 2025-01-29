package com.auth.server.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt-key}")
    private String SECRET; // secret istifade etme sebibimiz odurki tokeni sifrelerken bundan istifade edciyik.
    // Bunun sebebi ise token kiminse eline kecdikde bu secret-i bilmediyi muddetce tokeni decode ede bilmez

    public String generateToken(String email) {

        Map<String, Object> claims = new HashMap<>();

        return createToken(claims, email);

    }

    public Boolean validateToken(String token, UserDetails userDetails){
        String email = extractUser(token);
        Date expirationDate = extractExpiration(token);
        return userDetails.getUsername().equals(email) && expirationDate.after(new Date());
    }

    private Date extractExpiration(String token){
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(getSignKey()) // yuxardaki key-i istifade ederek tokeni decode edirik
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public String extractUser(String token){
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(getSignKey()) // yuxardaki key-i istifade ederek tokeni decode edirik
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    private String createToken(Map<String, Object> claims, String email){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis())) // token ne vaxt create olundugu tarixi avtomatik goturecek
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 7)) // tokenin omrunun ne qeder vaxt olacagini qeyd edirik
                .signWith(getSignKey(), SignatureAlgorithm.HS256)                   // 1000L * 60 * 60 * 24 * 7 - 1 hefte olacaq bu sekilde
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
