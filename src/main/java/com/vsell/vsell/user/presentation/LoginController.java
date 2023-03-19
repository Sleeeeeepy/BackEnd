package com.vsell.vsell.user.presentation;

import com.vsell.vsell.user.application.LoginService;
import com.vsell.vsell.user.dto.JwtTokenDto;
import com.vsell.vsell.user.dto.LoginDto;
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
    public ResponseEntity<JwtTokenDto> login(@RequestBody LoginDto loginDto){
        return new ResponseEntity<JwtTokenDto>(loginService.login(loginDto), HttpStatus.OK);
    }
}
