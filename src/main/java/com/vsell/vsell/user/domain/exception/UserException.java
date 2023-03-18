package com.vsell.vsell.user.domain.exception;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException{
    private final String message;
    private final String errorCode;

    public UserException(UserExceptionType userExceptionType){
        this.message=userExceptionType.getMessage();
        this.errorCode=userExceptionType.getErrorCode();
    }
}
