package com.akucheruk.bank_app.controller;

import com.akucheruk.bank_app.domain.dto.in.TransactionSearchRequest;
import com.akucheruk.bank_app.domain.entity.Transaction;
import com.akucheruk.bank_app.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Transaction controller", description = "Contains all operation with transactions")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Operation(summary = "Get transactions in page view")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "found transactions")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Page<Transaction> getTransactions(
            @ParameterObject TransactionSearchRequest searchRequest,
            @ParameterObject @PageableDefault(
                    page = 0,
                    size = 5,
                    sort = {"processedTime"},
                    direction = Sort.Direction.DESC) Pageable pageable) {
        return transactionService.getTransactions(searchRequest, pageable);
    }
}
