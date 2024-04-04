package com.kai.master_logic.services.credit.credit_payment;

import com.kai.master_logic.utils.DateTime;

import java.time.LocalDateTime;

public class BlockingPayCredit  {
    protected static Boolean isPayCreditBlocked;
    protected static LocalDateTime unlockTime;

    protected boolean canUnlockPayCredit() {
        if (DateTime.getCurrentDateTimeNotFormat().equals(unlockTime)) {
            unlockPayCredit();
            return true;
        } else {
            return false;
        }
    }

    protected void blockPayCredit() {
        isPayCreditBlocked = true;
        setUnlockTime();
    }

    protected void unlockPayCredit() {
        isPayCreditBlocked = false;
        putAwayUnlockTime();
    }

    private void setUnlockTime() {
        unlockTime = DateTime.getCurrentDateTimeNotFormat().plusHours(1);
    }

    private void putAwayUnlockTime() {
        unlockTime = null;
    }

}
