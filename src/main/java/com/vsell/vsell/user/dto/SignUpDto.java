package com.vsell.vsell.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

@Getter
@Setter
public class SignUpDto {

    @Schema(description = "email")
    private String email;
    @Schema(description = "password")
    private String password;

    @Schema(description = "이름")
    private String name;
    @Schema(description = "닉네임")
    private String nickName;
    @Schema(description = "생년월일(yyyy-mm-dd)")
    private String birthDate;

    public Instant getBirthDate() {
        return LocalDate.parse(birthDate).atStartOfDay().toInstant(ZoneOffset.UTC);
    }
}
