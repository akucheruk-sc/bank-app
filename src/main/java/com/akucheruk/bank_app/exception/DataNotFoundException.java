package com.akucheruk.bank_app.exception;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String parameter, Class<?> clazz) {
        super("Data not found by [%s]. Class: [%s]".formatted(parameter, clazz.getName()));
    }
}
