package com.vsell.vsell.auction.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AuctionException extends RuntimeException {
    private final String message;
    private final String errorCode;
    private final HttpStatus httpStatus;

    public AuctionException(AuctionExceptionType auctionExceptionType) {
        this.message = auctionExceptionType.getMessage();
        this.errorCode = auctionExceptionType.getErrorCode();
        this.httpStatus = auctionExceptionType.getHttpStatus();
    }
}
