package com.kai.master_logic.database.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
public class Check {
    private String senderPhoneNumber;
    private String senderFullName;
    private String recipientPhoneNumber;
    private String recipientFullName;
    private BigDecimal moneyAmount;
    private BigDecimal bonusAmount;
    private String dataOfBirth;
}
