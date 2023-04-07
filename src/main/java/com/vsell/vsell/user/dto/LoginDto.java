package com.vsell.vsell.user.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {

    @Schema(description = "email")
    private String email;
    @Schema(description = "password")
    private String password;
}
