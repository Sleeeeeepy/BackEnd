package com.vsell.vsell.user.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InformationModifyDto {
    private String email;
    private String changePassword;
    private String changeNickname;
}
