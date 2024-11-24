package com.akucheruk.bank_app.repository.transaction;

import com.akucheruk.bank_app.domain.entity.Transaction;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface TransactionRepository
        extends PagingAndSortingRepository<Transaction, UUID>, TransactionRepositoryCustom {
}
