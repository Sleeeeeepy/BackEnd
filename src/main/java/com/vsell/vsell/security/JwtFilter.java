package com.vsell.vsell.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vsell.vsell.response.ResponseStatusType;
import com.vsell.vsell.security.dto.SecurityResponseDto;
import com.vsell.vsell.security.exception.CustomSecurityException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    public JwtFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = jwtProvider.resolveToken(request);

        try{
            if(token != null){
                Authentication auth = jwtProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }catch(CustomSecurityException ex){
            setExceptionResponse(response, ex);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void setExceptionResponse(HttpServletResponse response, CustomSecurityException customSecurityException){
        ObjectMapper objectMapper = new ObjectMapper();

        response.setStatus(customSecurityException.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        SecurityResponseDto securityResponseDto = new SecurityResponseDto();
        securityResponseDto.setStatus(ResponseStatusType.FAIL);

        Map<String,Object> data = new HashMap<>();
        data.put("message", customSecurityException.getMessage());
        data.put("errorCode", customSecurityException.getErrorCode());
        securityResponseDto.setData(data);

        try {
            response.getWriter().write(objectMapper.writeValueAsString(securityResponseDto));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
