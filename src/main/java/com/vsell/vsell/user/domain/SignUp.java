package com.vsell.vsell.user.domain;

import com.vsell.vsell.user.domain.exception.CustomUserException;
import com.vsell.vsell.user.domain.exception.UserExceptionType;
import com.vsell.vsell.user.dto.SignUpDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SignUp {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public SignUp(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    public void signUp(SignUpDto signUpDto){
        if(!signUpDto.getPassword().equals(signUpDto.getPasswordAgain())){
            throw new CustomUserException(UserExceptionType.INVALID_PASSWORD_AGAIN);
        }
        if(isExistEmail(signUpDto.getEmail())){
            throw new CustomUserException(UserExceptionType.DUPLICATE_USER_EMAIL);
        }
        if(isExistNickName(signUpDto.getNickName())){
            throw new CustomUserException(UserExceptionType.DUPLICATE_USER_NICKNAME);
        }

        User user = new User.UserBuilder()
                            .name(signUpDto.getName())
                            .birthDate(signUpDto.getBirthDate())
                            .email(signUpDto.getEmail())
                            .nickName(signUpDto.getNickName())
                            .password(passwordEncoder.encode(signUpDto.getPassword()))
                            .build();

        userRepository.save(user);
    }

    private boolean isExistEmail(String email){
        if(userRepository.findByEmail(email) != null){
            return true;
        }
        return false;
    }

    private boolean isExistNickName(String nickName){
        if(userRepository.findByNickName(nickName) != null){
            return true;
        }
        return false;
    }
}
