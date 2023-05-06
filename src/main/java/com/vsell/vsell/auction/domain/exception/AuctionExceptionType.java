package com.vsell.vsell.auction.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum AuctionExceptionType {


    INVALID_AUCTION_TITLE("올바르지 않은 제목입니다.", "A001", HttpStatus.BAD_REQUEST),
    INVALID_AUCTION_BID("올바르지 않은 입찰가입니다.", "A002", HttpStatus.BAD_REQUEST),
    INVALID_AUCTION_START("경매 시작 시간이 올바르지 않습니다.", "A003", HttpStatus.BAD_REQUEST),
    INVALID_AUCTION_END("경매 종료 시간이 올바르지 않습니다.", "A004", HttpStatus.BAD_REQUEST),
    NOT_EXIST_AUCTION_FILE("아바타 파일이 존재하지 않습니다.", "A005", HttpStatus.BAD_REQUEST),
    INVALID_BID_TIME("경매 시간이 아닙니다.", "A006", HttpStatus.BAD_REQUEST),
    INVALID_BID_PRICE("경매 금액이 올바르지 않습니다.", "A007", HttpStatus.BAD_REQUEST),
    INVALID_CONTENT_IMG("올바른 이미지 파일이 아닙니다.", "A008", HttpStatus.UNSUPPORTED_MEDIA_TYPE),
    EXIST_FILE("이미 파일이 존재합니다.", "A009", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_AUCTION_CONTENT("올바르지 않은 내용입니다.", "A010", HttpStatus.BAD_REQUEST),
    NOT_EXIST_FILE("파일이 존재하지 않습니다.", "A011", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_AVATAR_TYPE("아바타 파일 타입이 적절하지 않습니다.", "A012", HttpStatus.UNSUPPORTED_MEDIA_TYPE),
    FORBIDDEN("권한이 없습니다.", "A013", HttpStatus.FORBIDDEN),
    NOT_EXIST_AUCTION("존재하지 않는 경매이빈다.", "a014", HttpStatus.BAD_REQUEST);

    private final String message;
    private final String errorCode;
    private final HttpStatus httpStatus;

    AuctionExceptionType(String message, String errorCode, HttpStatus httpStatus) {
        this.message = message;
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }
}
