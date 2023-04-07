package com.vsell.vsell.user.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomUserException extends RuntimeException {
    private final String message;
    private final String errorCode;
    private final HttpStatus httpStatus;

    public CustomUserException(UserExceptionType userExceptionType) {
        this.message = userExceptionType.getMessage();
        this.errorCode = userExceptionType.getErrorCode();
        this.httpStatus = userExceptionType.getHttpStatus();
    }
}
