package com.kai.master_logic.services.transfer;

public class TransferErrorsHandler {

    public void lacksBalance() {
        System.out.println("There is no enough balance to transfer");
    }

    public void lessMinAmount() {
        System.out.println("Minimum amount for transfer: 100 ₸");
    }

    public void moreMaxAmount() {
        System.out.println("Maximum amount for transfer: 5.000.000 ₸");
    }

    public void transferBlocked() {
        System.out.println("Transfer blocked for 5 minutes");
    }
}
