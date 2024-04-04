package com.kai.master_logic.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {
    private static final String FORMAT_PATTERN = "dd.MM.yyyy HH mm";

    public static LocalDateTime getCurrentDateTimeNotFormat() {
        return LocalDateTime.now();
    }

    public static String getCurrentDateTimeFormat() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_PATTERN);

        return currentDateTime.format(formatter);
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_PATTERN);
        return dateTime.format(formatter);
    }
}
