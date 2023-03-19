package com.vsell.vsell.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

@Getter
@Setter
public class SignUpDto {

    private String email;
    private String password;
    private String passwordAgain;

    private String name;
    private String nickName;
    private String birthDate;

    public Instant getBirthDate() {
        return LocalDate.parse(birthDate).atStartOfDay().toInstant(ZoneOffset.UTC);
    }
}
