package com.vsell.vsell.user.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InformationModifyDto {

    @Schema(description = "email")
    private String email;

    @Schema(description = "변경할 패스워드", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String changePassword;

    @Schema(description = "변경할 닉네임", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String changeNickname;
}
