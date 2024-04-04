package com.kai.master_logic.services.credit.credit_payment;


import com.kai.master_logic.database.models.Credit;
import com.kai.master_logic.database.models.User;

public class ValidatePayCredit {
    enum validateStatus {
        READY_TO_PAY_CREDIT,
        LACKS_BALANCE,

    }

    protected validateStatus validatePayCredit(User user, Credit credit) {
        if (user.getBalance().compareTo(credit.getMonthlyPayment()) < 0)
        {
            return validateStatus.LACKS_BALANCE;
        }

        return validateStatus.READY_TO_PAY_CREDIT;
    }

}
