package com.kai.master_logic.utils;

public class Format {
    public static String formatFullName(String firstName, String lastName) {
        return firstName.substring(0,1) + "." + lastName;
    }
}
