package com.kai.master_logic.database.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
public class Deposit {
    private Long depositID;
    private Long userID;
    private BigDecimal amountDeposit;
    private BigDecimal interest;
    private LocalDateTime nextDateIncreaseAmountDeposit;
}
