package com.kai.master_logic.services.credit.give_credit;

public class CreditErrorHandler {
    public void ageError() {
        System.out.println("Not available age (min 18 years)");
    }
    public void blockGiveCredit() {
        System.out.println("Credit give is blocked, please try again after 5 hours");
    }
}
