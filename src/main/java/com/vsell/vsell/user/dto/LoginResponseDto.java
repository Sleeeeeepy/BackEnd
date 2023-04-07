package com.vsell.vsell.user.dto;

import com.vsell.vsell.response.ResponseStatusType;
import com.vsell.vsell.security.dto.JwtTokenDto;
import lombok.Getter;

@Getter
public class LoginResponseDto {
    private String status;
    private JwtTokenDto data;

    public void setStatus(ResponseStatusType responseStatusType){
        this.status = responseStatusType.getStatus();
    }

    public void setData(JwtTokenDto jwtTokenDto){
        this.data = jwtTokenDto;
    }
}
