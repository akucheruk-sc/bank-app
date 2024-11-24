package com.akucheruk.bank_app.domain.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false, exclude = {"address", "accounts"})
@JsonPropertyOrder(value = {
        "clientId", "firstName", "lastName", "middleName",
        "isActive", "isActive", "createDate", "modifyDate",
        "address", "accounts"
})
@Entity
@Table(name = "client")
public class Client extends StartAndUpdateDate {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID clientId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String middleName;

    private boolean isActive = true;

    @ManyToOne
    @JoinColumn(name="address_id", nullable=false)
    private Address address;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "client_to_account",
            joinColumns = { @JoinColumn(name = "client_id") },
            inverseJoinColumns = { @JoinColumn(name = "account_id") }
    )
    private List<Account> accounts;
}
