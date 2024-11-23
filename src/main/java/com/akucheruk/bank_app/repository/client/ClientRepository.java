package com.akucheruk.bank_app.repository.client;

import com.akucheruk.bank_app.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository
        extends JpaRepository<Client, UUID>, ClientRepositoryCustom {

}
