package com.akucheruk.bank_app.repository;

import com.akucheruk.bank_app.domain.entity.Address;
import com.akucheruk.bank_app.domain.entity.AddressState;

import java.util.List;

public interface AddressRepositoryCustom {
    List<Address> findAllByStateAndPostCodeAndStreet(AddressState state,
                                                     Integer postCode,
                                                     String street);
}
