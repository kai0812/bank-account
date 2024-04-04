package com.kai.master_logic.services.credit.give_credit;

import com.sun.jdi.IntegerType;

import java.math.BigDecimal;

public class ValidationToGiveCredit {

    private static final BigDecimal AFTER_AMOUNT_TAKE_INTEREST = BigDecimal.valueOf(1000000);
    private static final BigDecimal INTEREST_ON_LARGE_CREDIT_AMOUNT = new BigDecimal(2.0); // 2 percent


    enum statusOfValidation {
        READY_TO_ISSUE_CREDIT,
        AGE_ERROR,
        NOT_ENOUGH_FUNDS_TO_PAY_INTEREST
    }

    protected statusOfValidation validation() {
        if (GiveCredit.creditTaken.getId() < 18)
        {
            return statusOfValidation.AGE_ERROR;
        }

        if (GiveCredit.amount.compareTo(AFTER_AMOUNT_TAKE_INTEREST) >= 0) {
            if (GiveCredit.amount.multiply(INTEREST_ON_LARGE_CREDIT_AMOUNT).compareTo(GiveCredit.amount) < 0) {
                return statusOfValidation.NOT_ENOUGH_FUNDS_TO_PAY_INTEREST;
            }
        }



        return statusOfValidation.READY_TO_ISSUE_CREDIT;
    }

}
