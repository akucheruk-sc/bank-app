package com.akucheruk.bank_app.domain.dto.in;

import com.akucheruk.bank_app.domain.entity.TransactionStatus;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
public class TransactionSearchRequest {
    private Long lessThanAmount;
    private Long moreThanAmount;
    @Pattern(regexp = "^\\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$")    //yyyy-mm-dd
    private String startFromDate;
    @Pattern(regexp = "^\\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$")    //yyyy-mm-dd
    private String endToDate;
    private List<TransactionStatus> statuses;
}
