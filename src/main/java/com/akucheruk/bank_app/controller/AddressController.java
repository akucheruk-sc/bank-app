package com.akucheruk.bank_app.controller;

import com.akucheruk.bank_app.domain.dto.AddressDto;
import com.akucheruk.bank_app.domain.entity.Address;
import com.akucheruk.bank_app.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("address")
public class AddressController {

    private final AddressService addressService;

    @Operation(summary = "Get addresses by state and postCode")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "found addresses"),
            @ApiResponse(responseCode = "400", description = "postCode not valid")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Address> getAddresses(
            @RequestParam(required = false) String state,
            @RequestParam(required = false) @Min(100000) @Max(999999) Integer postCode,
            @RequestParam(required = false) String street) {
        return addressService.getAddresses(state, postCode, street);
    }

    @Operation(summary = "Add new addresses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "addresses added"),
            @ApiResponse(responseCode = "400", description = "input body is not valid")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Address createAddress(@Valid @RequestBody AddressDto addressDto) {
        return addressService.createAddress(addressDto);
    }

    @Operation(summary = "Update current addresses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "addresses updated"),
            @ApiResponse(responseCode = "400", description = "arguments not valid"),
            @ApiResponse(responseCode = "404", description = "address not found"),
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.ACCEPTED) //TODO fix?
    public Address updateAddress(
            @PathVariable UUID id,
            @RequestBody AddressDto addressDto) {
        return addressService.updateAddress(id, addressDto);
    }

    @Operation(summary = "Delete current addresses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "addresses deleted"),
            @ApiResponse(responseCode = "404", description = "address not found"),
    })
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteAddress(@PathVariable UUID id) {
        addressService.deleteAddress(id);
    }


}
