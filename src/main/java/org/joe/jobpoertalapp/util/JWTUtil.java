package org.joe.jobpoertalapp.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtil {
    private final String secret;
    private final SecretKey key;
    private final long expirationTime = (1000*60*60);//1 hour

    public JWTUtil(@Value("${JWT_SECRET}") String secret) {
        this.secret = secret;
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String username,Long id,String role) {
        return Jwts.builder().setSubject(username)
                .claim("role",role)
                .claim("id", id)
                .setIssuedAt(new Date())
                .setExpiration(new  Date(System.currentTimeMillis() + expirationTime))
                .signWith(key, SignatureAlgorithm.HS256).compact();
    }

    public String extractUsername(String token) {
        Claims body = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return body.getSubject();
    }

    public boolean validateToken(String token) {
       return  !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Claims body = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return body.getExpiration().before(new Date());
    }
}
