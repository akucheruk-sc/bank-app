package com.akucheruk.bank_app.service;

import com.akucheruk.bank_app.domain.dto.in.ClientDto;
import com.akucheruk.bank_app.domain.dto.in.ClientSearchRequest;
import com.akucheruk.bank_app.domain.entity.Address;
import com.akucheruk.bank_app.domain.entity.Client;
import com.akucheruk.bank_app.exception.DataNotFoundException;
import com.akucheruk.bank_app.mapper.ClientMapper;
import com.akucheruk.bank_app.repository.address.AddressRepository;
import com.akucheruk.bank_app.repository.client.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;

    private final ClientMapper clientMapper;

    public List<Client> getClients(String firstName, String lastName, Boolean isActive) {
        var requestObj = ClientSearchRequest.builder()
                .firstName(firstName)
                .lastName(lastName)
                .isActive(isActive)
                .build();
        return clientRepository.findAllByClientRequestObj(requestObj);
    }

    @Transactional
    public Client createClient(ClientDto clientDto) {
        return clientRepository.save(clientMapper.dtoToEntity(clientDto));
    }

    @Transactional
    public Client updateClient(UUID id, ClientDto clientDto) {
        var currentClient = clientRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("clientId: " + id, Client.class));
        currentClient = clientMapper.updateClient(currentClient, clientDto);
        return clientRepository.save(currentClient);
    }

    @Transactional
    public void deleteClient(UUID id) {
        clientRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("clientId: " + id, Client.class));
        clientRepository.deleteById(id);
    }

    @Transactional
    public Client updateClientAddress(UUID clientId, UUID addressId) {
        var existClient = clientRepository
                .findById(clientId)
                .orElseThrow(() -> new DataNotFoundException("clientId: " + clientId, Client.class));
        var existAddress = addressRepository
                .findById(addressId)
                .orElseThrow(() -> new DataNotFoundException("addressId: " + addressId, Address.class));
        existClient.setAddress(existAddress);

        return clientRepository.save(existClient);
    }

}
