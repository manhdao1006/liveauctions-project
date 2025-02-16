package com.ute.auction.security;

import java.security.Key;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JWTGenerator {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private final BlackList blackList;

    public String generateToken(UserDetails userDetails) {
        String username = userDetails.getUsername();
        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + SecurityConstants.ACCESS_TOKEN_EXPIRATION);

        String token = Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return token;
    }

    public String generateRefreshToken(String username) {
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + SecurityConstants.REFRESH_TOKEN_EXPIRATION);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public String refreshAccessToken(String refreshToken) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(refreshToken)
                    .getBody();

            String username = claims.getSubject();
            return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.ACCESS_TOKEN_EXPIRATION))
                    .signWith(key, SignatureAlgorithm.HS512)
                    .compact();
        } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            throw new AuthenticationCredentialsNotFoundException("Invalid refresh token");
        }
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    // kiểm tra tính hợp lệ của JWT
    public boolean validateToken(String token) {
        try {
            if (blackList.isBlackListed(token)) {
                throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
            }

            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
        }
    }

    public boolean validateRefreshToken(String token) {
        try {
            if (blackList.isBlackListed(token)) {
                throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
            }

            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            throw new AuthenticationCredentialsNotFoundException("Refresh token was expired or incorrect");
        }
    }

}
