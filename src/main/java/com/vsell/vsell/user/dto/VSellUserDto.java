package com.vsell.vsell.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
public class VSellUserDto {

    @Schema(description = "email")
    private String email;
    @Schema(description = "nickname")
    private String nickName;
    @Schema(description = "생년월일(yyyy-mm-dd)", defaultValue = "yyyy-mm-dd")
    private Instant birthDate;
}
