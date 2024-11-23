package com.akucheruk.bank_app.repository.client;

import com.akucheruk.bank_app.domain.dto.in.ClientRequest;
import com.akucheruk.bank_app.domain.entity.Client;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface ClientRepositoryCustom {
    List<Client> findAllByClientRequestObj(@NotNull ClientRequest clientRequest);
}
