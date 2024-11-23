package com.akucheruk.bank_app.domain.dto.out;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ErrorMsg {
    private HttpStatus httpStatus;
    private String errorMessage;
}
