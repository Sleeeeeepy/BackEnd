package com.vsell.vsell.user.dto;

import com.vsell.vsell.response.ResponseStatusType;
import com.vsell.vsell.security.dto.JwtTokenDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class LoginResponseDto {
    @Schema(description = "성공 여부(fail or success)")
    private String status;
    @Schema(description = "jwt 토큰")
    private JwtTokenDto data;

    public void setStatus(ResponseStatusType responseStatusType) {
        this.status = responseStatusType.getStatus();
    }

    public void setData(JwtTokenDto jwtTokenDto) {
        this.data = jwtTokenDto;
    }
}
