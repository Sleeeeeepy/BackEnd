package com.vsell.vsell.security.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum SecurityExceptionType {
    INVALID_TOKEN("유효하지 않은 토큰 형식 입니다.", "S001", HttpStatus.BAD_REQUEST),
    EXPIRE_TOKEN("토큰이 만료되었습니다.", "S002", HttpStatus.BAD_REQUEST)
    ;

    private final String message;
    private final String errorCode;
    private final HttpStatus httpStatus;

    SecurityExceptionType(String message, String errorCode, HttpStatus httpStatus){
        this.message = message;
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }
}
