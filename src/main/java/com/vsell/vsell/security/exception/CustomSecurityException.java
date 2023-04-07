package com.vsell.vsell.security.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomSecurityException extends RuntimeException {
    private final String message;
    private final String errorCode;
    private final HttpStatus httpStatus;

    public CustomSecurityException(SecurityExceptionType securityExceptionType) {
        this.message = securityExceptionType.getMessage();
        this.errorCode = securityExceptionType.getErrorCode();
        this.httpStatus = securityExceptionType.getHttpStatus();
    }
}
