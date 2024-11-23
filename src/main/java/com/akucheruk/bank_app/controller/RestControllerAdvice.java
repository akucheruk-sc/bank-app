package com.akucheruk.bank_app.controller;

import com.akucheruk.bank_app.domain.dto.out.ErrorMsg;
import com.akucheruk.bank_app.exception.DataAlreadyExistException;
import com.akucheruk.bank_app.exception.DataNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
public class RestControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException ex) {
        log.warn("Handle {} exception, message: {}", ex.getClass().getName(), ex.getMessage());
        var errMsg = ErrorMsg.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .errorMessage(ex.getMessage())
                .build();

        return new ResponseEntity<>(errMsg, errMsg.getHttpStatus());
    }

    @ExceptionHandler(DataAlreadyExistException.class)
    public ResponseEntity<Object> handleDataAlreadyExistException(DataAlreadyExistException ex) {
        log.warn("Handle {} exception, message: {}", ex.getClass().getName(), ex.getMessage());
        var errMsg = ErrorMsg.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .errorMessage(ex.getMessage())
                .build();

        return new ResponseEntity<>(errMsg, errMsg.getHttpStatus());
    }

}
