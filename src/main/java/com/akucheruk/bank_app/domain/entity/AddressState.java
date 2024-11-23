package com.akucheruk.bank_app.domain.entity;

import java.util.stream.Stream;

public enum AddressState {
    MINNESOTA,
    NORTH_CAROLINA,
    TEXAS,
    WASHINGTON,
    OTHER;
    //TODO add states

    public static AddressState fromString(String str) {
        return Stream.of(AddressState.values())
                .filter(value -> value.name().equalsIgnoreCase(str))
                .findAny()
                .orElse(OTHER);
    }


}
