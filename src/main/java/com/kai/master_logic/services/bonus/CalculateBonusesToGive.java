package com.kai.master_logic.services.bonus;

import java.math.BigDecimal;

public class CalculateBonusesToGive {
    private static final BigDecimal BONUS_PERCENT_TRANSFER = new BigDecimal("0.02");
    
    public BigDecimal calculateBonusToGive(BigDecimal amount) {
        return amount.multiply(BONUS_PERCENT_TRANSFER);
    }
}
