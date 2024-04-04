package com.kai.master_logic.services.transfer;

import com.kai.master_logic.database.models.Bonus;
import com.kai.master_logic.database.models.Check;
import com.kai.master_logic.database.models.Transfer;
import com.kai.master_logic.database.repository.BonusRepository;
import com.kai.master_logic.database.repository.TransferRepository;
import com.kai.master_logic.database.repository.UserRepository;
import com.kai.master_logic.services.bonus.CalculateBonusesToGive;
import com.kai.master_logic.utils.DateTime;
import com.kai.master_logic.utils.Format;

import java.math.BigDecimal;


public class ProcessingTransfer {
    private final TransferRepository transferRepository;
    private final UserRepository userRepository;
    private final BonusRepository bonusRepository;
    private final IssueCheck issueCheck;
    private final CalculateBonusesToGive calculateBonusesToGive;


    public ProcessingTransfer() {
        this.transferRepository = new TransferRepository();
        this.userRepository = new UserRepository();
        this.bonusRepository = new BonusRepository();
        this.calculateBonusesToGive = new CalculateBonusesToGive();
        this.issueCheck = new IssueCheck();
    }


    public void processing() {
        // Updating current data of transfer participants
        updateAccountsData();

        if (TransferMoney.isIssueCheck)
        {
            issueCheck.issueCheckATransfer(getCurrentlyCheckObject());
        }

        // Transfer completed
        transferRepository.addTransfer(getCurrentlyTransferObject());
    }

    private void updateAccountsData() {
        // Updating cash accounts of transfer participants
        TransferMoney.sender.setBalance(TransferMoney.sender.getBalance().subtract(TransferMoney.amount));
        TransferMoney.recipient.setBalance(TransferMoney.sender.getBalance().add(TransferMoney.amount));

        userRepository.updateUser(TransferMoney.sender);
        userRepository.updateUser(TransferMoney.recipient);

        // Updating cash account (sender)
        BigDecimal newBonusBalance = calculateBonusesToGive.calculateBonusToGive(TransferMoney.sender.getBonus());
        bonusRepository.updateBonusAccount(new Bonus(TransferMoney.sender.getId(), newBonusBalance));
    }

    private Transfer getCurrentlyTransferObject() {
        return new Transfer(
                TransferMoney.sender.getId(),
                TransferMoney.recipient.getId(),
                TransferMoney.amount,
                calculateBonusesToGive.calculateBonusToGive(TransferMoney.amount),
                TransferMoney.comment, DateTime.getCurrentDateTimeNotFormat()
        );
    }


    private Check getCurrentlyCheckObject() {
        return new Check (
                TransferMoney.sender.getPhoneNumber(),
                Format.formatFullName(TransferMoney.sender.getFirstName(), TransferMoney.sender.getLastName()),
                TransferMoney.recipient.getPhoneNumber(),
                Format.formatFullName(TransferMoney.recipient.getFirstName(), TransferMoney.recipient.getLastName()),
                TransferMoney.amount,
                calculateBonusesToGive.calculateBonusToGive(TransferMoney.amount),
                DateTime.getCurrentDateTimeFormat()
        );
    }

}
