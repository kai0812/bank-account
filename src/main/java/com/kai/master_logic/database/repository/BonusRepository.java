package com.kai.master_logic.database.repository;

import com.kai.master_logic.database.models.Bonus;

import java.util.ArrayList;
import java.util.List;

public class BonusRepository {
    private List<Bonus> bonusRepository;

    public BonusRepository() {
        this.bonusRepository = new ArrayList<>();
    }

    public void updateBonusAccount(Bonus bonus) {
        bonusRepository.remove(bonus);
        bonusRepository.add(bonus);
    }



}
