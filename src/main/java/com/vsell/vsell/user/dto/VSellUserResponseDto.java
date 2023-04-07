package com.vsell.vsell.user.dto;

import com.vsell.vsell.response.ResponseStatusType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VSellUserResponseDto {
    private String status;
    private DataDto data = new DataDto();

    public void setStatus(ResponseStatusType responseStatusType){
        this.status = responseStatusType.getStatus();
    }

    public void setData(VSellUserDto vSellUserDto){
        this.data.setUser(vSellUserDto);
    }

    @Setter
    @Getter
    public static class DataDto{
        private VSellUserDto user;
    }
}
