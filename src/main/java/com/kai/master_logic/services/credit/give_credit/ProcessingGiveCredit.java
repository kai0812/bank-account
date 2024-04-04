package com.kai.master_logic.services.credit.give_credit;

import com.kai.master_logic.database.models.Credit;
import com.kai.master_logic.database.repository.CreditsRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.time.LocalDateTime;

public class ProcessingGiveCredit {
    private final CreditsRepository creditRepository;
    private final SecureRandom random;


    public ProcessingGiveCredit() {
        this.creditRepository = new CreditsRepository();
        this.random = new SecureRandom();
    }


    public void processing() {
        final BigDecimal DEFAULT_CREDIT_PAID_AMOUNT = BigDecimal.valueOf(0);

        Credit credit = new Credit(
                generateCreditID(),
                GiveCredit.creditTaken.getId(),
                GiveCredit.amount,
                DEFAULT_CREDIT_PAID_AMOUNT,
                calculateMonthlyPayment(),
                calculateNextPaymentDate()
        );

        creditRepository.addCredit(credit);
    }



    private BigDecimal calculateMonthlyPayment() {
        final byte THREE_MONTHS = 3;
        final byte SIX_MONTHS = 6;
        final byte TWELVE_MONTHS = 12;
        final byte TWENTY_FOUR_MONTHS = 24;

        BigDecimal monthlyPayment = BigDecimal.valueOf(1); // default

        switch (GiveCredit.creditTerm)
        {
            case THREE_MONTHS:
                monthlyPayment = GiveCredit.amount.divide(BigDecimal.valueOf(THREE_MONTHS), 2, RoundingMode.HALF_UP);
                break;
            case SIX_MONTHS:
                monthlyPayment = GiveCredit.amount.divide(BigDecimal.valueOf(SIX_MONTHS), 2, RoundingMode.HALF_UP);
                break;
            case TWELVE_MONTHS:
                monthlyPayment = GiveCredit.amount.divide(BigDecimal.valueOf(TWELVE_MONTHS), 2, RoundingMode.HALF_UP);
                break;
            case TWENTY_FOUR_MONTHS:
                monthlyPayment = GiveCredit.amount.divide(BigDecimal.valueOf(TWENTY_FOUR_MONTHS), 2, RoundingMode.HALF_UP);
                break;
        }

        return monthlyPayment;
    }

    private LocalDateTime calculateNextPaymentDate() {
        return LocalDateTime.now().plusMonths(1);
    }

    private String generateCreditID() {
        final String MARK = "credit-";
        return MARK + random.nextInt(123457890);
    }

}
