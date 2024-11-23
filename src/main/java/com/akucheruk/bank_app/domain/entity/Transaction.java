package com.akucheruk.bank_app.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false, exclude = {"account"})
@Entity
@Table(name = "transaction")
public class Transaction extends StartAndUpdateDate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID trasactionId;

    @NotNull
    private BigDecimal amount;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date processedTime;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus status;

    @ManyToOne
    @JoinColumn(name="account_id", nullable=false)
    private Account account;

    private String fromAccountNumber;

    private String toAccountNumber;
}
