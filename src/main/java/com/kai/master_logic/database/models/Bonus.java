package com.kai.master_logic.database.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;




@Data
@AllArgsConstructor
public class Bonus {
    private Long userID;
    private BigDecimal balance;
}
