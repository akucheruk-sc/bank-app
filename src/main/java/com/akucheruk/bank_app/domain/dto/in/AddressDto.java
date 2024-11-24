package com.akucheruk.bank_app.domain.dto.in;

import com.akucheruk.bank_app.domain.entity.AddressState;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressDto {

    @NotBlank
    private String houseNumber;

    @NotBlank
    private String street;

    @Min(100000)
    @Max(999999)
    private Integer postCode;

    @NotNull
    private AddressState state;
}
