package com.vsell.vsell.user.presentation;

import com.vsell.vsell.response.ResponseStatusType;
import com.vsell.vsell.user.application.SignUpService;
import com.vsell.vsell.user.domain.SignUp;
import com.vsell.vsell.user.dto.SignUpDto;
import com.vsell.vsell.user.dto.SignUpResponseDto;
import io.netty.handler.codec.http.HttpStatusClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class SignUpController {
    private final SignUpService signUpService;

    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpDto signUpDto){
        signUpService.signUp(signUpDto);

        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
        signUpResponseDto.setData(null);
        signUpResponseDto.setStatus(ResponseStatusType.SUCCESS);

        return new ResponseEntity<SignUpResponseDto>(signUpResponseDto, HttpStatus.OK);
    }
}
