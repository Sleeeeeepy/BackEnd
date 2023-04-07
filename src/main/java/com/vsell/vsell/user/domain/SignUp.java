package com.vsell.vsell.user.domain;

import com.vsell.vsell.user.domain.exception.CustomUserException;
import com.vsell.vsell.user.domain.exception.UserExceptionType;
import com.vsell.vsell.user.dto.SignUpDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SignUp {
    private final PasswordEncoder passwordEncoder;
    private final VSellUserRepository userRepository;

    public SignUp(PasswordEncoder passwordEncoder, VSellUserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    public void signUp(SignUpDto signUpDto){
        if(isExistEmail(signUpDto.getEmail())){
            throw new CustomUserException(UserExceptionType.DUPLICATE_USER_EMAIL);
        }
        if(isExistNickName(signUpDto.getNickName())){
            throw new CustomUserException(UserExceptionType.DUPLICATE_USER_NICKNAME);
        }

        VSellUser user = new VSellUser.VSellUserBuilder()
                            .passwordEncoder(passwordEncoder)
                            .name(signUpDto.getName())
                            .birthDate(signUpDto.getBirthDate())
                            .email(signUpDto.getEmail())
                            .nickName(signUpDto.getNickName())
                            .password(signUpDto.getPassword())
                            .build();

        userRepository.save(user);
    }

    private boolean isExistEmail(String email){
        try{
            userRepository.findByEmail(email);
        }
        catch(CustomUserException ex){
            if(ex.getErrorCode().equals(UserExceptionType.NOT_EXIST_EMAIL.getErrorCode()))
                return false;
        }
        return true;
    }

    private boolean isExistNickName(String nickName){
        try{
            userRepository.findByNickName(nickName);
        }
        catch(CustomUserException ex){
            if(ex.getErrorCode().equals(UserExceptionType.NOT_EXIST_NICKNAME.getErrorCode()))
                return false;
        }
        return true;
    }
}
