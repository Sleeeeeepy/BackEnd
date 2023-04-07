package com.vsell.vsell.response;

import lombok.Getter;

import java.util.Map;

@Getter
public class SimpleResponseDto {
    private String status;
    private Map<String, Object> data;

    public void setStatus(ResponseStatusType responseStatusType) {
        this.status = responseStatusType.getStatus();
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
