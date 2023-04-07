package com.vsell.vsell.user.presentation;

import com.vsell.vsell.response.ResponseStatusType;
import com.vsell.vsell.user.application.SignUpService;
import com.vsell.vsell.user.dto.SignUpDto;
import com.vsell.vsell.response.SimpleResponseDto;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<SimpleResponseDto> signUp(@RequestBody SignUpDto signUpDto){
        signUpService.signUp(signUpDto);

        SimpleResponseDto signUpResponseDto = new SimpleResponseDto();
        signUpResponseDto.setData(null);
        signUpResponseDto.setStatus(ResponseStatusType.SUCCESS);

        return new ResponseEntity<SimpleResponseDto>(signUpResponseDto, HttpStatus.OK);
    }
}
