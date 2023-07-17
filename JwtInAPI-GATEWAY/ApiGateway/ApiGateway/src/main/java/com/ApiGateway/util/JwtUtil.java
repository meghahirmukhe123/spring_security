package com.ApiGateway.util;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {
	
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    
    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
	
    }


    

   

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
