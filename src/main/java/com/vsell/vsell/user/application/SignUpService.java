package com.vsell.vsell.user.application;


import com.vsell.vsell.user.domain.SignUp;
import com.vsell.vsell.user.dto.SignUpDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SignUpService {
    private final SignUp signUp;

    public SignUpService(SignUp signUp) {
        this.signUp = signUp;
    }

    @Transactional
    public void signUp(SignUpDto signUpDto){
        signUp.signUp(signUpDto);
    }


}
