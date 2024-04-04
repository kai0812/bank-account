package com.kai.master_logic.services.transfer;


import com.kai.master_logic.database.models.User;

import java.math.BigDecimal;


public class TransferMoney {
    protected static User sender;
    protected static User recipient;
    protected static BigDecimal amount;
    protected static String comment;
    protected static Boolean isIssueCheck;

    private static Integer countToBlockTransfer;

    private final ValidateTransfer validateTransfer;
    private final ProcessingTransfer processingTransfer;
    private final TransferErrorsHandler transferErrorsHandler;
    private final BlockingTransfer blockingTransfer;



    public TransferMoney() {
        this.validateTransfer = new ValidateTransfer();
        this.processingTransfer = new ProcessingTransfer();
        this.transferErrorsHandler = new TransferErrorsHandler();
        this.blockingTransfer = new BlockingTransfer();
    }



    public void execute() {
        if (BlockingTransfer.isTransferBlocked) {
            if (blockingTransfer.canUnblockTransfer()) {
                // Transfer unblocked
                execute();
            } else {
                // Перевод заблокирован, обробатываем этот случай
                transferErrorsHandler.transferBlocked();
            }
        } else {
            // Вызываем валидацию для перевода средств
            ValidateTransfer.statusOfValidate statusOfValidate = validateTransfer.validate();

            // Оброботка статусов от валидатора
            if (statusOfValidate == (ValidateTransfer.statusOfValidate.READY_FOR_TRANSFER)) {
                processingTransfer.processing();
            } else {
                // Обработка не валидного перевода
                switch (statusOfValidate)
                {
                    case LACKS_BALANCE:
                        transferErrorsHandler.lacksBalance();
                        break;
                    case LESS_MIN_AMOUNT:
                        transferErrorsHandler.lessMinAmount();
                        break;
                    case MORE_MAX_AMOUNT:
                        transferErrorsHandler.moreMaxAmount();
                        break;
                }

                // Вызов метод для возможной блокировки трансфера
                handleToMaybeBlockTransfer();
            }
        }
    }

    private void handleToMaybeBlockTransfer() {
        final int BLOCK_TRANSFER_AFTER = 5;

        if (countToBlockTransfer >= BLOCK_TRANSFER_AFTER) {
            countToBlockTransfer = 0;
            blockingTransfer.blockTransfer();
        } else {
            countToBlockTransfer++;
        }
    }


}



