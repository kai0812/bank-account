package com.kai.master_logic.services.credit.give_credit;

import com.kai.master_logic.utils.DateTime;

import java.time.LocalDateTime;

public class BlockingCreditGet {
    protected static Boolean isCreditGiveBlocked;
    protected static LocalDateTime unblockTime;

    protected void blockGiveCredit() {
        isCreditGiveBlocked = true;
        setUnblockTime();
    }

    protected void unblockCredit() {
        isCreditGiveBlocked = false;
        putAwayUnblockTime();
    }

    protected boolean canUnblockGiveCredit() {
        if (DateTime.getCurrentDateTimeNotFormat().equals(unblockTime)) {
            unblockCredit();
            return true;
        } else {
            return false;
        }
    }


    private void setUnblockTime() {
        if (unblockTime != null) {
            throw new IllegalArgumentException();
        } else {
            unblockTime = DateTime.getCurrentDateTimeNotFormat().plusHours(3);
        }
    }

    private void putAwayUnblockTime() {
        if (unblockTime == null) {
            throw new IllegalArgumentException();
        } else {
            unblockTime = null;
        }
     }
}
