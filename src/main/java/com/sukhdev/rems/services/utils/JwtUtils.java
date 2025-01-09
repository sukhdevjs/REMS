package com.sukhdev.rems.services.utils;

import com.sukhdev.rems.entity.User;
import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class JwtUtils {
    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }




    public String generateToken(UserDetails  userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String userName = extractUserName(token);
        return(userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    private <T> T extractClaim(String token, Function<Claims, T> claimResolvers){
        final Claims claims = extractAllClaims(token);
        return claimResolvers.apply(claims);
    }
    private String generateToken(Map<String, Object> extractClaims, UserDetails userDetails) {
        extractClaims.put("roles", userDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
        return Jwts.builder()
                .setClaims(extractClaims) // Add claims from the map
                .setSubject(userDetails.getUsername()) // Set the subject (username)
                .setIssuedAt(new Date(System.currentTimeMillis())) // Set issued time
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) // Set expiration
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Sign with the key
                .compact(); // Generate the token
    }

    public String generateFreshToken(Map<String,Object> extractClaims, UserDetails userDetails){
            return Jwts.builder().setClaims(extractClaims)
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 604800000))
                    .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token){
       return extractClaim(token, Claims::getExpiration);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody();
    }
    private Key getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode("QWERTYUIOP1234567890ASDFGHJKL1234567890ZXCVBNM");
        return Keys.hmacShaKeyFor(keyBytes);
    }}

