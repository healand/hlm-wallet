package com.last.lastcoin.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(CustomResponseStatusException.class)
    public ResponseEntity<ExceptionResponse> handleException(CustomResponseStatusException e) {
        return ExceptionResponse.toResponseEntity(e.getExceptionCode(), e.getMessage());
    }
}
