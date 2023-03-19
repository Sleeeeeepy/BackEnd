package com.vsell.vsell.user.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class SignUpDto {

    private String email;
    private String password;
    private String passwordAgain;

    private String name;
    private String nickName;
    private Instant birthDate;
}
