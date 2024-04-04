package com.kai.master_logic.services.credit.credit_payment;

import com.kai.master_logic.database.models.Credit;
import com.kai.master_logic.database.models.User;
import com.kai.master_logic.database.repository.CreditsRepository;
import com.kai.master_logic.database.repository.UserRepository;



public class PayCredit {
    private final UserRepository userRepository;
    private final CreditsRepository creditsRepository;
    private final ValidatePayCredit validatePayCredit;
    private final PayCreditErrorHandler payCreditErrorHandler;
    private final BlockingPayCredit blockingPayCredit;


    public PayCredit() {
        this.userRepository = new UserRepository();
        this.creditsRepository = new CreditsRepository();
        this.validatePayCredit = new ValidatePayCredit();
        this.payCreditErrorHandler = new PayCreditErrorHandler();
        this.blockingPayCredit = new BlockingPayCredit();
    }

    public void execute(User user, Credit credit, Boolean isSystemRequest) {
        if (!isSystemRequest) {
            if (BlockingPayCredit.isPayCreditBlocked) {
                if (blockingPayCredit.canUnlockPayCredit()) {
                    execute(user, credit, isSystemRequest);
                } else {
                    payCreditErrorHandler.payCreditIsBlocked();
                }
            } else {
                if (validatePayCredit.validatePayCredit(user, credit).equals(ValidatePayCredit.validateStatus.READY_TO_PAY_CREDIT)) {
                    updateUserData(user, credit);
                } else if (validatePayCredit.validatePayCredit(user, credit).equals(ValidatePayCredit.validateStatus.LACKS_BALANCE)) {
                    payCreditErrorHandler.lacksBalanceToPaymentCredit();
                }
            }

        } else {
            updateUserData(user, credit);
        }
    }

    private void updateUserData(User user, Credit credit) {
        // update balances
        user.setBalance(user.getBalance().subtract(credit.getMonthlyPayment()));
        credit.setPaidCreditAmount(credit.getPaidCreditAmount().add(credit.getMonthlyPayment()));

        userRepository.updateUser(user);
        creditsRepository.updateDataCredit(credit);
    }

}
