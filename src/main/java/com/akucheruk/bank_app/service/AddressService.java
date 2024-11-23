package com.akucheruk.bank_app.service;

import com.akucheruk.bank_app.domain.dto.AddressDto;
import com.akucheruk.bank_app.domain.entity.Address;
import com.akucheruk.bank_app.domain.entity.AddressState;
import com.akucheruk.bank_app.exception.DataAlreadyExistException;
import com.akucheruk.bank_app.exception.DataNotFoundException;
import com.akucheruk.bank_app.mapper.AddressMapper;
import com.akucheruk.bank_app.repository.AddressRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public List<Address> getAddresses(String state, Integer postCode, String street) {
        var stateEnum = StringUtils.hasText(state) ? AddressState.fromString(state) : null;
        return addressRepository.findAllByStateAndPostCodeAndStreet(stateEnum, postCode, street);
    }

    @Transactional
    public Address createAddress(AddressDto addressDto) {
        addressRepository.findAllByStateAndPostCodeAndStreet(
                addressDto.getState(),
                addressDto.getPostCode(),
                addressDto.getStreet())
                .stream()
                .filter(address -> address.getHouseNumber().equalsIgnoreCase(addressDto.getHouseNumber()))
                .findAny()
                .ifPresent((address) -> {
                    throw new DataAlreadyExistException("Address already exist in DB: " + addressDto);
                });
        var newAddress = addressMapper.dtoToEntity(addressDto);

        return addressRepository.save(newAddress);
    }

    @Transactional
    public Address updateAddress(UUID id, AddressDto addressDto) {
        var currentAddress = addressRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("id: " + id, Address.class));
        currentAddress = addressMapper.updateAddress(currentAddress, addressDto);
        return addressRepository.save(currentAddress);
    }

    @Transactional
    public void deleteAddress(UUID id) {
        addressRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("id: " + id, Address.class));
        addressRepository.deleteById(id);
    }
/*
    public Page<AddressView> getAddressesByState(AddressState state, Pageable pageable) {
        return addressRepository.findAllByState(state, pageable);
    }

    public Page<AddressView> getAddressesByPostCode(Integer postCode, Pageable pageable) {
        return addressRepository.findAllByPostCode(postCode, pageable);
    }*/

}
