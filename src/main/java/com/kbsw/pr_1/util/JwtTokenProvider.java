package com.kbsw.pr_1.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String SecretKey;

    @Value("${jwt.expiration}")
    private long expirationTime;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SecretKey.getBytes(StandardCharsets.UTF_8));
    }

    // JWT 생성
    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);
        Key key = getSigningKey();
        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(key)
                .compact();

    }

    // JWT에서 사용자명 추출
    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // JWT가 유효한지 확인
    public boolean isTokenValid(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getExpiration().before(new Date());
    }
}

