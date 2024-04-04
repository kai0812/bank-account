package com.kai.master_logic.services.transfer;


import java.math.BigDecimal;

public class ValidateTransfer {

    private static final BigDecimal MIN_TRANSFER_AMOUNT = BigDecimal.valueOf(100);
    private static final BigDecimal MAX_TRANSFER_AMOUNT = BigDecimal.valueOf(5000000);

    enum statusOfValidate {
        READY_FOR_TRANSFER,
        LESS_MIN_AMOUNT,
        MORE_MAX_AMOUNT,
        LACKS_BALANCE
    }


    public statusOfValidate validate() {
        // check if there is enough balance for the transfer
        if (TransferMoney.sender.getBalance().compareTo(TransferMoney.amount) < 0) {
            return statusOfValidate.LACKS_BALANCE;
        }

        // min amount check
        if (TransferMoney.amount.compareTo(MIN_TRANSFER_AMOUNT) < 0) {
            return statusOfValidate.LESS_MIN_AMOUNT;
        }

        // max amount check
        if (TransferMoney.amount.compareTo(MAX_TRANSFER_AMOUNT) > 0) {
            return statusOfValidate.MORE_MAX_AMOUNT;
        }

        return statusOfValidate.READY_FOR_TRANSFER;
    }





}





