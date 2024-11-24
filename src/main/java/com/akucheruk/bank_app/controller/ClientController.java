package com.akucheruk.bank_app.controller;

import com.akucheruk.bank_app.domain.dto.in.ClientDto;
import com.akucheruk.bank_app.domain.entity.Client;
import com.akucheruk.bank_app.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Tag(name = "Client controller", description = "Contains all operation with clients")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("client")
public class ClientController {

    private final ClientService clientService;

    @Operation(summary = "Get clients by attributes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "found clients")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Client> getClients(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) Boolean isActive) {
        return clientService.getClients(firstName, lastName, isActive);
    }

    @Operation(summary = "Add new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "client added"),
            @ApiResponse(responseCode = "400", description = "input body is not valid")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Client createClient(@Valid @RequestBody ClientDto clientDto) {
        return clientService.createClient(clientDto);
    }

    @Operation(summary = "Update current client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "client updated"),
            @ApiResponse(responseCode = "404", description = "client not found"),
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Client updateClient(
            @PathVariable UUID id,
            @RequestBody ClientDto clientDto) {
        return clientService.updateClient(id, clientDto);
    }

    @Operation(summary = "Delete current client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "client deleted"),
            @ApiResponse(responseCode = "404", description = "client not found"),
    })
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteClient(@PathVariable UUID id) {
        clientService.deleteClient(id);
    }

    @Operation(summary = "Update address for exist client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "address updated"),
            @ApiResponse(responseCode = "404", description = "client or address not found"),
    })
    @PutMapping(value = "/{clientId}/address/{addressId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Client updateClientAddress(
            @PathVariable UUID clientId,
            @PathVariable UUID addressId) {
        return clientService.updateClientAddress(clientId, addressId);
    }


}
