package com.akucheruk.bank_app.util;

import com.akucheruk.bank_app.exception.BankAppRuntimeException;
import jakarta.validation.constraints.NotBlank;
import lombok.experimental.UtilityClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@UtilityClass
public class DateUtils {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static Date parseDate(@NotBlank String dateStr) {
        try {
            return SIMPLE_DATE_FORMAT.parse(dateStr);
        } catch (ParseException pe) {
            throw new BankAppRuntimeException("Failed to parse date: " + dateStr, pe);
        }
    }

}
