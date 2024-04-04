package com.kai.master_logic.logs.erros_logs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorModel {
    private Long id;
    private String error;
    private String fromClass;
    private String fromMethod;
    private String dateOfBirth;
}
