package com.akucheruk.bank_app.repository;

import com.akucheruk.bank_app.domain.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository
        extends JpaRepository<Address, UUID>, AddressRepositoryCustom {

}
