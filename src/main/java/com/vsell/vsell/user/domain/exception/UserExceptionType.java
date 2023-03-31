package com.vsell.vsell.user.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum UserExceptionType {
    INVALID_USER_NAME("올바르지 않은 이름 형식 입니다.", "U001", HttpStatus.BAD_REQUEST),
    INVALID_USER_EMAIL("올바르지 않은 이메일 형식 입니다.", "U002", HttpStatus.BAD_REQUEST),
    INVALID_USER_NICKNAME("올바르지 않은 닉네임 형식 입니다.", "U003", HttpStatus.BAD_REQUEST),
    INVALID_USER_PASSWORD("올바르지 않은 패스워드 형식 입니다.", "U004", HttpStatus.BAD_REQUEST),
    INVALID_USER_BIRTHDATE("올바르지 않은 생년원일 형식 입니다.", "U005", HttpStatus.BAD_REQUEST),

    DUPLICATE_USER_NICKNAME("중복된 닉네임이 있습니다.", "U006", HttpStatus.BAD_REQUEST),
    DUPLICATE_USER_EMAIL("중복된 이메일이 있습니다.", "U007", HttpStatus.BAD_REQUEST),

    FAIL_LOGIN("이메일 또는 비밀번호를 잘못 입력했습니다.", "U008", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD_AGAIN("비밀번호가 다릅니다.", "U009", HttpStatus.BAD_REQUEST),
    
    NOT_EXIST_EMAIL("존재하지 않는 이메일 입니다.", "U010", HttpStatus.BAD_REQUEST),
    NOT_EXIST_NICKNAME("존재하지 않는 유저입니다.", "U011", HttpStatus.BAD_REQUEST)
    ;

    private final String message;
    private final String errorCode;
    private final HttpStatus httpStatus;

    UserExceptionType(String message, String errorCode, HttpStatus httpStatus){
        this.message = message;
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }


}
