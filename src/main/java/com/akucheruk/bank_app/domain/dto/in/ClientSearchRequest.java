package com.akucheruk.bank_app.domain.dto.in;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClientSearchRequest {
    String firstName;
    String lastName;
    Boolean isActive;
}
