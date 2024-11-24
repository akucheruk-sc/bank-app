package com.akucheruk.bank_app.repository.transaction;

import com.akucheruk.bank_app.domain.dto.in.TransactionSearchRequest;
import com.akucheruk.bank_app.domain.entity.Transaction;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionRepositoryCustom {
    Page<Transaction> findAllByTransactionSearchRequestObj(
            @NotNull TransactionSearchRequest transactionSearchRequest,
            @NotNull Pageable page);
}
