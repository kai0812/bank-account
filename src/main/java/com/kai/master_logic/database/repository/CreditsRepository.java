package com.kai.master_logic.database.repository;

import com.kai.master_logic.database.models.Credit;

import java.util.ArrayList;
import java.util.List;

public class CreditsRepository {
    private List<Credit> credits;

    public CreditsRepository() {
        this.credits = new ArrayList<>();
    }

    public List<Credit> getAllCredits() {
        return credits;
    }



    public void addCredit(Credit credit) {
        credits.add(credit);
    }

    public void updateDataCredit(Credit credit) {
        if (credits.contains(credit)) {

        }
    }

    public Credit getCreditById(Long id) {
        Credit foundedCredit = null;

        for (Credit credit : credits) {
            if (credit.getUserID().equals(id)) {
                foundedCredit = credit;
            }
        }

        return foundedCredit;
    }

    public void cancelCredit(Credit credit) {
        credits.remove(credit);
    }


}
