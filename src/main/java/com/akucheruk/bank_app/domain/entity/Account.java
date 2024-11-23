package com.akucheruk.bank_app.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false, exclude = {"client"})
@Entity
@Table(name = "account")
public class Account extends StartAndUpdateDate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID accountId;

    @NotNull
    private String number;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private AccountType type;

    private boolean isActive = true;

    @NotNull
    private BigDecimal amount = BigDecimal.ZERO;

    @ManyToMany(mappedBy = "accounts")
    private List<Client> client;

    @OneToMany(mappedBy="account")
    private List<Transaction> transactions;
}
