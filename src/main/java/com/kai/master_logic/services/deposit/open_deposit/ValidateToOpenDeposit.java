package com.kai.master_logic.services.deposit.open_deposit;

import java.math.BigDecimal;

public class ValidateToOpenDeposit {
    private static final BigDecimal MIN_DEPOSIT_AMOUNT = BigDecimal.valueOf(1000);


    enum validateStatus {
        READY_TO_OPEN_DEPOSIT,
        LESS_MIN_AMOUNT
    }



    protected validateStatus validateOpenDeposit() {
        if (OpenDeposit.amount.compareTo(MIN_DEPOSIT_AMOUNT) < 0) {
            return validateStatus.LESS_MIN_AMOUNT;
        }

        return validateStatus.READY_TO_OPEN_DEPOSIT;
    }
}
