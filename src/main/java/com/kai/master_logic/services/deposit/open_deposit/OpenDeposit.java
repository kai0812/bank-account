package com.kai.master_logic.services.deposit.open_deposit;

import com.kai.master_logic.database.models.Deposit;
import com.kai.master_logic.database.models.User;

import java.math.BigDecimal;

public class OpenDeposit {
    protected static User user;
    protected static Deposit deposit;
    protected static BigDecimal amount;


    private final ValidateToOpenDeposit validateToOpenDeposit;

    public OpenDeposit() {
        this.validateToOpenDeposit = new ValidateToOpenDeposit();
    }

    public void execute() {

    }

    private void handleToMaybeBlockOpenDeposit() {

    }


}
