package com.kai.master_logic.services.credit.give_credit;

import com.kai.master_logic.database.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
public class GiveCredit {
    protected static User creditTaken;
    protected static BigDecimal amount;
    protected static Byte creditTerm;


    private static Integer countOfInvalidGiveCredit;

    private final ProcessingGiveCredit processingGiveCredit;
    private final ValidationToGiveCredit validationToGiveCredit;
    private final BlockingCreditGet blockingCreditGet;
    private final CreditErrorHandler creditErrorHandler;

    public GiveCredit() {
        this.processingGiveCredit = new ProcessingGiveCredit();
        this.validationToGiveCredit = new ValidationToGiveCredit();
        this.blockingCreditGet = new BlockingCreditGet();
        this.creditErrorHandler = new CreditErrorHandler();
    }


    public void execute() {
        if (BlockingCreditGet.isCreditGiveBlocked) {
            if (blockingCreditGet.canUnblockGiveCredit()) {
                execute();
            } else {
                creditErrorHandler.blockGiveCredit();
            }
        } else {
            // Вызываем метод валидаций
            ValidationToGiveCredit.statusOfValidation validateStatus = validationToGiveCredit.validation();

            if (validateStatus.equals(ValidationToGiveCredit.statusOfValidation.READY_TO_ISSUE_CREDIT)) {
                processingGiveCredit.processing();
            } else if (validateStatus.equals(ValidationToGiveCredit.statusOfValidation.AGE_ERROR)) {
                handleMaybeBlockGiveCredit();
                creditErrorHandler.ageError();
            }
        }
    }

    private void handleMaybeBlockGiveCredit() {
        final int BLOCK_TRANSFER_AFTER = 5;

        if (countOfInvalidGiveCredit == BLOCK_TRANSFER_AFTER) {
            blockingCreditGet.blockGiveCredit();
            countOfInvalidGiveCredit = 0;
        } else {
            countOfInvalidGiveCredit++;
        }
    }
}
