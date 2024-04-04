package com.kai.master_logic.database.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;



@Data
@AllArgsConstructor
public class Transfer {
    private Long senderID;
    private Long recipientID;
    private BigDecimal amount;
    private BigDecimal bonusIssued;
    private String comment;
    private LocalDateTime dataOfBirth;
}
