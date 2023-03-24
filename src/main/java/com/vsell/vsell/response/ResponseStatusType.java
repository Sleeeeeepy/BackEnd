package com.vsell.vsell.response;


import lombok.Getter;

@Getter
public enum ResponseStatusType {
    FAIL("fail"),
    SUCCESS("success");

    private final String status;

    ResponseStatusType(String status){
        this.status=status;
    }
}
