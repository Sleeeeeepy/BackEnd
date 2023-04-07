package com.vsell.vsell.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtTokenDto {

    @Schema(description = "accessToken")
    private String accessToken;
    @Schema(description = "refreshToken")
    private String refreshToken;
}
