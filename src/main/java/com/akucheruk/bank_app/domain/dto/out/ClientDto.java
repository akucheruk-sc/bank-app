package com.akucheruk.bank_app.domain.dto.out;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClientDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String middleName;
}
