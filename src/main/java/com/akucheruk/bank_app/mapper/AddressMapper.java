package com.akucheruk.bank_app.mapper;

import com.akucheruk.bank_app.domain.dto.in.AddressDto;
import com.akucheruk.bank_app.domain.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AddressMapper {
    Address dtoToEntity(AddressDto addressDto);
    Address updateAddress(@MappingTarget Address address, AddressDto addressDto);
}
