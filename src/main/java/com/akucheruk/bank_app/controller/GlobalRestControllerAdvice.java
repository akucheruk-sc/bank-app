package com.akucheruk.bank_app.controller;

import com.akucheruk.bank_app.domain.dto.out.ErrorMsg;
import com.akucheruk.bank_app.exception.BankAppRuntimeException;
import com.akucheruk.bank_app.exception.DataAlreadyExistException;
import com.akucheruk.bank_app.exception.DataNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Hidden
@Slf4j
@RestControllerAdvice(basePackages = {"com.akucheruk.bank_app.controller"})
public class GlobalRestControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException ex) {
        log.warn("Handle {} exception, message: {}", ex.getClass().getName(), ex.getMessage());
        var errMsg = ErrorMsg.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .errorMessage(ex.getMessage())
                .build();

        return new ResponseEntity<>(errMsg, errMsg.getHttpStatus());
    }

    @ExceptionHandler(exception = {DataAlreadyExistException.class, ConstraintViolationException.class})
    public ResponseEntity<Object> handleException(Exception ex) {
        log.warn("Handle {} exception, message: {}", ex.getClass().getName(), ex.getMessage());
        var errMsg = ErrorMsg.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .errorMessage(ex.getMessage())
                .build();

        return new ResponseEntity<>(errMsg, errMsg.getHttpStatus());
    }

    @ExceptionHandler(BankAppRuntimeException.class)
    public ResponseEntity<Object> handleDataBankAppRuntimeException(BankAppRuntimeException ex) {
        log.warn("Handle {} exception, message: {}", ex.getClass().getName(), ex.getMessage());
        var errMsg = ErrorMsg.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorMessage(ex.getMessage())
                .build();

        return new ResponseEntity<>(errMsg, errMsg.getHttpStatus());
    }

}
