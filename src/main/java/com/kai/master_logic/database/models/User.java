package com.kai.master_logic.database.models;

import lombok.Data;

import java.math.BigDecimal;

@Data

public class User {
    private Long id;
    private String phoneNumber;
    private String password;

    private String firstName;
    private String lastName;
    private Byte age;

    private BigDecimal balance;
    private BigDecimal bonus;
}
