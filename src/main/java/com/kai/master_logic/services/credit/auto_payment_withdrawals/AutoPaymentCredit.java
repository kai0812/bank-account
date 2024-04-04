package com.kai.master_logic.services.credit.auto_payment_withdrawals;


import com.kai.master_logic.database.models.Credit;
import com.kai.master_logic.database.models.User;
import com.kai.master_logic.database.repository.CreditsRepository;
import com.kai.master_logic.database.repository.UserRepository;
import com.kai.master_logic.services.credit.credit_payment.PayCredit;
import com.kai.master_logic.utils.DateTime;



import java.util.HashMap;
import java.util.Map;

public class AutoPaymentCredit {
    private final PayCredit payCredit;
    private final CreditsRepository creditsRepository;
    private final UserRepository userRepository;

    public AutoPaymentCredit() {
        this.payCredit = new PayCredit();
        this.creditsRepository = new CreditsRepository();
        this.userRepository = new UserRepository();
    }

    public void execute() {
        for (Map.Entry<User, Credit> entry : collectUsersAvailableToPayment().entrySet()) {
            payCredit.execute(entry.getKey(), entry.getValue(), true);
        }
    }

    private HashMap<User, Credit> collectUsersAvailableToPayment() {
        HashMap<User, Credit> toReturn = new HashMap<>();

        for (User user : userRepository.getAllUsers()) {
            for (Credit credit : creditsRepository.getAllCredits()) {
                if (credit.getCreditPaymentDate().equals(DateTime.getCurrentDateTimeNotFormat())) {
                    toReturn.put(user, credit);
                }
            }
        }

        return toReturn;
    }

}
