package com.vsell.vsell.user.dto;

import com.vsell.vsell.response.ResponseStatusType;
import lombok.Getter;
import lombok.Setter;

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
