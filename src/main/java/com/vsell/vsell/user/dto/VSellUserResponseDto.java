package com.vsell.vsell.user.dto;

import com.vsell.vsell.response.ResponseStatusType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VSellUserResponseDto {

    @Schema(description = "성공 여부(fail or success)")
    private String status;

    @Schema(description = "응답 데이터")
    private DataDto data = new DataDto();

    public void setStatus(ResponseStatusType responseStatusType) {
        this.status = responseStatusType.getStatus();
    }

    public void setData(VSellUserDto vSellUserDto) {
        this.data.setUser(vSellUserDto);
    }

    @Setter
    @Getter
    public static class DataDto {
        @Schema(description = "유저 정보")
        private VSellUserDto user;
    }
}

