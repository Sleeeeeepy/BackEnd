package com.vsell.vsell.user.presentation;

import com.vsell.vsell.response.ResponseStatusType;
import com.vsell.vsell.security.dto.JwtTokenDto;
import com.vsell.vsell.user.application.LoginService;
import com.vsell.vsell.user.dto.LoginDto;
import com.vsell.vsell.user.dto.LoginResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto) {
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        JwtTokenDto jwtTokenDto = loginService.login(loginDto);

        loginResponseDto.setStatus(ResponseStatusType.SUCCESS);
        loginResponseDto.setData(jwtTokenDto);

        return new ResponseEntity<LoginResponseDto>(loginResponseDto, HttpStatus.OK);
    }

}
