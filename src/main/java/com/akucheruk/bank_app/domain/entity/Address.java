package com.akucheruk.bank_app.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false, exclude = {"client"})
@Entity
@Table(name = "address")
public class Address extends StartAndUpdateDate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "address_id")
    private UUID addressId;

    @NotBlank
    private String houseNumber;

    @NotBlank
    private String street;

    @NotNull
    private int postCode;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private AddressState state;

    @JsonBackReference
    @OneToOne(mappedBy = "address")
    private Client client;
}
