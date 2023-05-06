package com.vsell.vsell.auction.domain.exception;

import com.vsell.vsell.response.ResponseStatusType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AuctionExceptionHandler {

    @ExceptionHandler(AuctionException.class)
    public ResponseEntity<Map<String, Object>> handleCustomSecurityException(AuctionException ex) {
        Map<String, Object> res = new HashMap<>();
        res.put("status", ResponseStatusType.FAIL.getStatus());

        Map<String, Object> data = new HashMap<>();
        data.put("errorCode", ex.getErrorCode());
        data.put("message", ex.getMessage());

        res.put("data", data);

        return new ResponseEntity<>(res, ex.getHttpStatus());
    }
}
