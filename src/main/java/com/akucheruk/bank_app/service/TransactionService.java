package com.akucheruk.bank_app.service;

import com.akucheruk.bank_app.domain.dto.in.TransactionSearchRequest;
import com.akucheruk.bank_app.domain.entity.Transaction;
import com.akucheruk.bank_app.repository.transaction.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public Page<Transaction> getTransactions(TransactionSearchRequest searchRequest, Pageable pageable) {
        return transactionRepository.findAllByTransactionSearchRequestObj(searchRequest, pageable);
    }
}
