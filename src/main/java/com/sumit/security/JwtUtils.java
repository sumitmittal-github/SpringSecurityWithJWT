package com.sumit.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Log4j2
public class JwtUtils {

    @Value("${jwt.token.256.bit.secret.key}")
    private String SECRET_KEY = null;

    @Value("${jwt.token.expiry.duration}")
    private Long TOKEN_EXPIRY_DURATION = null;

    public String generateToken(String username){
        return createToken(username, new HashMap<>());
    }

    private String createToken(String username, Map<String, Object> extraClaims){
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(username)
                .header().empty().add("typ","JWT")
                .and()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRY_DURATION))  // 30 minutes expiry time
                .signWith(getSignInKey())
                .compact();
    }

    // reading Token's Payload (extracting all Claims means reading all attributes from the Token Payload
    private Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }




    private SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String extractUsername(String token){
        return extractAllClaims(token).getSubject();
    }

    private Date extractTokenExpiry(String token) {
        return extractAllClaims(token).getExpiration();
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractTokenExpiry(token).before(new Date());
    }

}