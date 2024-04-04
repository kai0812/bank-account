package com.kai.master_logic.services.transfer;

import com.kai.master_logic.utils.DateTime;
import java.time.LocalDateTime;

public class BlockingTransfer {
    protected static Boolean isTransferBlocked;
    protected static LocalDateTime unblockTime;

    protected void blockTransfer() {
        isTransferBlocked = true;
        setUnblockTime();
    }

    protected void unblockTransfer() {
        isTransferBlocked = false;
        putAwayUnblockTime();
    }

    protected boolean canUnblockTransfer() {
        if (DateTime.getCurrentDateTimeNotFormat().equals(unblockTime)) {
            unblockTransfer();
            return true;
        } else {
            return false;
        }
    }


    private void setUnblockTime() {
        if (unblockTime == null) {
            unblockTime = DateTime.getCurrentDateTimeNotFormat().plusMinutes(5);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void putAwayUnblockTime() {
        unblockTime = null;
    }
}

