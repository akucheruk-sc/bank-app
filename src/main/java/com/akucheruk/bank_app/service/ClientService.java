package com.akucheruk.bank_app.service;

import com.akucheruk.bank_app.domain.dto.in.ClientRequest;
import com.akucheruk.bank_app.domain.entity.Client;
import com.akucheruk.bank_app.mapper.ClientMapper;
import com.akucheruk.bank_app.repository.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public List<Client> getClients(String firstName, String lastName, Boolean isActive) {
        var requestObj = ClientRequest.builder()
                .firstName(firstName)
                .lastName(lastName)
                .isActive(isActive)
                .build();
        return clientRepository.findAllByClientRequestObj(requestObj);
    }
}
