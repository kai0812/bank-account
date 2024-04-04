package com.kai.master_logic.services.credit.credit_payment;

public class PayCreditErrorHandler {
    public void lacksBalanceToPaymentCredit() {
        System.out.println("Lacks balance");
    }
    public void payCreditIsBlocked() {
        System.out.println("Pay credit is blocked, please try again of 1 hours...");
    }
}

