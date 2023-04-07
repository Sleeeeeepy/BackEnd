package com.vsell.vsell.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
public class VSellUserDto {
    private String email;
    private String nickName;
    private Instant birthDate;
}
