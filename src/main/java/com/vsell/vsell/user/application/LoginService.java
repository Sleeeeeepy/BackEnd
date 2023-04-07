package com.vsell.vsell.user.application;

import com.vsell.vsell.security.dto.JwtTokenDto;
import com.vsell.vsell.user.domain.Login;
import com.vsell.vsell.user.dto.LoginDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {
    private final Login login;

    public LoginService(Login login) {
        this.login = login;
    }

    @Transactional
    public JwtTokenDto login(LoginDto loginDto) {
        return login.login(loginDto.getEmail(), loginDto.getPassword());
    }
}
