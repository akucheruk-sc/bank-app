package com.akucheruk.bank_app.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false, exclude = {"account"})
@JsonPropertyOrder(value = {
        "transactionId", "amount", "description", "status", "createDate",
        "modifyDate", "processedTime", "fromAccountNumber", "toAccountNumber"
})
@Entity
@Table(name = "transaction")
public class Transaction extends StartAndUpdateDate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID transactionId;

    @NotNull
    private BigDecimal amount;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date processedTime;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus status;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="account_id", nullable=false)
    private Account account;

    private String fromAccountNumber;

    private String toAccountNumber;
}
