package com.akucheruk.bank_app.mapper;

import com.akucheruk.bank_app.domain.dto.out.ClientDto;
import com.akucheruk.bank_app.domain.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClientMapper {
    Client dtoToEntity(ClientDto clientDto);
    Client updateAddress(@MappingTarget Client client, ClientDto clientDto);
}
