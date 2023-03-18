package com.vsell.vsell.user.domain.exception;

import lombok.Getter;

@Getter
public enum UserExceptionType {
    INVALID_USER_NAME("올바르지 않은 이름 형식 입니다.", "U001"),
    INVALID_USER_EMAIL("올바르지 않은 이메일 형식 입니다.", "U002"),
    INVALID_USER_NICKNAME("올바르지 않은 닉네임 형식 입니다.", "U003"),
    INVALID_USER_PASSWORD("올바르지 않은 패스워드 형식 입니다.", "U004"),
    INVALID_USER_BIRTHDATE("올바르지 않은 생년원일 형식 입니다.", "U005"),

    DUPLICATE_USER_NICKNAME("중복된 닉네임이 있습니다.", "U006"),
    DUPLICATE_USER_EMAIL("중복된 이메일이 있습니다.", "U007"),

    FAIL_LOGIN("이메일 또는 비밀번호를 잘못 입력했습니다.", "U008"),
    ;




    private final String message;
    private final String errorCode;

    UserExceptionType(String message, String errorCode){
        this.message = message;
        this.errorCode = errorCode;
    }


}
