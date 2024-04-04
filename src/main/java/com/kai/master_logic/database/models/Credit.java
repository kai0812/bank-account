package com.kai.master_logic.database.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
public class Credit {
    private String creditID;
    private Long userID;
    private BigDecimal creditAmount;
    private BigDecimal paidCreditAmount;
    private BigDecimal monthlyPayment;
    private LocalDateTime creditPaymentDate;
}
