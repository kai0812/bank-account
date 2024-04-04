package com.kai.master_logic.services.transfer;

import com.kai.master_logic.database.models.Check;
import com.kai.master_logic.database.models.Transfer;
import com.kai.master_logic.database.repository.BonusRepository;

import java.io.*;
import java.security.SecureRandom;
import java.time.temporal.JulianFields;
import java.util.List;
import java.util.Random;

public class IssueCheck {
    private final SecureRandom secureRandom;


    private static final String APP_LOGO = "Kai.kz";
    private static final String BOARD = "========";

    public IssueCheck() {
        this.secureRandom = new SecureRandom();
    }

    public void issueCheckATransfer(Check check) {
        if (check == null)
        {
            throw new IllegalArgumentException();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(generateCheckID()))) {
            writer.println(APP_LOGO);
            writer.println(BOARD);
            writer.println("ОТПРАВИТЕЛЬ: " + check.getSenderPhoneNumber());
            writer.println(check.getSenderFullName().toUpperCase());
            writer.println(BOARD);
            System.out.println("ПОЛУЧАТЕЛЬ: ");
            writer.println(check.getRecipientPhoneNumber());
            writer.println(check.getRecipientFullName().toUpperCase());
            writer.println(BOARD);
            System.out.println("СУММА ПЕРЕВОДА: " + check.getMoneyAmount());
            writer.println(BOARD);
            System.out.println("НАЧИСЛЕНО БОНУСОВ: " + check.getBonusAmount());
            writer.println(BOARD);
            System.out.println(check.getDataOfBirth());
            writer.println(BOARD);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateCheckID() {
        final String MARK = "transfer-";
        final String FILE_TYPE = ".txt";

        int id = secureRandom.nextInt(1234567890);

        return MARK + id + FILE_TYPE;
    }

}
