package com.vsell.vsell.security;

import com.vsell.vsell.security.exception.CustomSecurityException;
import com.vsell.vsell.security.exception.SecurityExceptionType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.access.secret}")
    private String accessSecretKey;
    @Value("${jwt.refresh.secret}")
    private String refreshSecretKey;

    @Value("${jwt.access.expire-ms}")
    private String accessExpireMs;
    @Value("${jwt.refresh.expire-ms}")
    private String refreshExpireMs;

    @PostConstruct protected void init(){
        accessSecretKey = Base64.getEncoder().encodeToString(accessSecretKey.getBytes());
        refreshSecretKey = Base64.getEncoder().encodeToString(refreshSecretKey.getBytes());
    }

    public String createAccessToken(String email){
        Claims claims = Jwts.claims().setSubject(email);

        Date now = new Date();
        Date validDate = new Date(now.getTime() + accessExpireMs);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validDate)
                .signWith(SignatureAlgorithm.HS256, accessSecretKey)
                .compact();
    }

    public String createRefreshToken(String email){
        Claims claims = Jwts.claims().setSubject(email);

        Date now = new Date();
        Date validDate = new Date(now.getTime() + refreshExpireMs);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validDate)
                .signWith(SignatureAlgorithm.HS256, refreshSecretKey)
                .compact();
    }

    public void validateAccessToken(String token){
        try{
            Jwts.parser().setSigningKey(accessSecretKey).parseClaimsJws(token);
        } catch(ExpiredJwtException e){
            throw new CustomSecurityException(SecurityExceptionType.EXPIRE_TOKEN);
        } catch (Exception e){
            throw new CustomSecurityException(SecurityExceptionType.INVALID_TOKEN);
        }
    }

    public void validateRefreshToken(String token){
        try{
            Jwts.parser().setSigningKey(refreshSecretKey).parseClaimsJws(token);
        } catch(ExpiredJwtException e){
            throw new CustomSecurityException(SecurityExceptionType.EXPIRE_TOKEN);
        } catch (Exception e){
            throw new CustomSecurityException(SecurityExceptionType.INVALID_TOKEN);
        }
    }

    public String resolveToken(HttpServletRequest req){
        String token = req.getHeader("Authorization");

        if(token != null){
            return token;
        }

        return null;
    }

}