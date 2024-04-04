package com.kai.master_logic.database.repository;


import com.kai.master_logic.database.models.Transfer;

import java.util.ArrayList;
import java.util.List;

public class TransferRepository {
    private List<Transfer> transfers;

    public TransferRepository() {
        this.transfers = new ArrayList<>();
    }

    public List<Transfer> getAllTransfers() {
        return transfers;
    }

    public void addTransfer(Transfer transfer) {
        if (transfer != null) {
            transfers.add(transfer);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public List<Transfer> getUserTransfersById(Long userID) {
        if (userID != null && userID != 0) {
            List<Transfer> userTransfers = new ArrayList<>();
            for (Transfer transfer : transfers) {
                if (transfer.getSenderID().equals(userID))
                {
                    userTransfers.add(transfer);
                }
            }

            return userTransfers;
        } else {
            throw new IllegalArgumentException();
        }
    }


}

