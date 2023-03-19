package com.vsell.vsell.user.domain;

import com.vsell.vsell.user.domain.exception.CustomUserException;
import com.vsell.vsell.user.domain.exception.UserExceptionType;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.regex.Pattern;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length=50, unique = true, nullable = false)
    private String email;

    @Column(length=20, nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(length=10, unique = true, nullable = false)
    private String nickName;

    @Column(nullable = false)
    private Instant birthDate;


    @Builder
    public User(String name, String email, String password, String nickName, Instant birthDate){
        assertValidName(name);
        assertValidEmail(email);
        assertValidPassword(password);
        assertValidNickName(nickName);
        assertValidBirthDate(birthDate);

        this.name = name;
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.birthDate = birthDate;

    }

    private void assertValidName(String name){
        if(name == null){
            throw new CustomUserException(UserExceptionType.INVALID_USER_NAME);
        }
        if(name.length() > 255){
            throw new CustomUserException(UserExceptionType.INVALID_USER_NAME);
        }
    }

    private void assertValidEmail(String email){
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if(email==null){
            throw new CustomUserException(UserExceptionType.INVALID_USER_EMAIL);
        }
        if(!Pattern.compile(regexPattern).matcher(email).matches()){
            throw new CustomUserException(UserExceptionType.INVALID_USER_EMAIL);
        }
        if(email.length() > 50){
            throw new CustomUserException(UserExceptionType.INVALID_USER_EMAIL);
        }
    }

    private void assertValidPassword(String password){
        if(password == null){
            throw new CustomUserException(UserExceptionType.INVALID_USER_PASSWORD);
        }
        if(password.length() > 20){
            throw new CustomUserException(UserExceptionType.INVALID_USER_PASSWORD);
        }
    }

    private void assertValidNickName(String nickName){
        if(nickName == null){
            throw new CustomUserException(UserExceptionType.INVALID_USER_NICKNAME);
        }
        if(nickName.length() > 10){
            throw new CustomUserException(UserExceptionType.INVALID_USER_NICKNAME);
        }
    }

    private void assertValidBirthDate(Instant birthDate){
        if(birthDate == null){
            throw new CustomUserException(UserExceptionType.INVALID_USER_BIRTHDATE);
        }
    }

}
