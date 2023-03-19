package com.vsell.vsell.security.exception;


import com.vsell.vsell.response.ResponseStatusType;
import com.vsell.vsell.user.domain.exception.CustomUserException;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(CustomUserException.class)
    public ResponseEntity<Map<String,Object>> handleCustomSecurityException(CustomUserException ex){
        Map<String, Object> res = new HashMap<>();
        res.put("status", ResponseStatusType.FAIL.getStatus());

        Map<String, Object> data = new HashMap<>();
        data.put("errorCode", ex.getErrorCode());
        data.put("message", ex.getMessage());

        res.put("data", data);

        return new ResponseEntity<>(res, ex.getHttpStatus());
    }
}
