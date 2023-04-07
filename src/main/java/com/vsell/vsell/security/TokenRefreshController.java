package com.vsell.vsell.security;

import com.vsell.vsell.security.dto.JwtTokenDto;
import com.vsell.vsell.security.exception.CustomSecurityException;
import com.vsell.vsell.security.exception.SecurityExceptionType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenRefreshController {


    private final JwtProvider jwtProvider;

    public TokenRefreshController(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/token")
    public ResponseEntity<JwtTokenDto> refreshToken(@RequestBody JwtTokenDto jwtTokenDto) {
        jwtProvider.validateRefreshToken(jwtTokenDto.getRefreshToken());
        String accessTokenEmail = jwtProvider.getAccessTokenEmail(jwtTokenDto.getAccessToken());
        String refreshTokenEmail = jwtProvider.getRefreshTokenEmail(jwtTokenDto.getRefreshToken());

        JwtTokenDto response = new JwtTokenDto();

        if (!accessTokenEmail.equals(refreshTokenEmail)) {
            throw new CustomSecurityException(SecurityExceptionType.DIF_TOKEN_EMAIL);
        }

        response.setAccessToken(jwtProvider.createAccessToken(accessTokenEmail));
        response.setRefreshToken(jwtProvider.createRefreshToken(refreshTokenEmail));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
