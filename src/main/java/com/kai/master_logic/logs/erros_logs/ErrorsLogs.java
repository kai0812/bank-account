package com.kai.master_logic.logs.erros_logs;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class ErrorsLogs {
    private List<ErrorModel> logs;

    public ErrorsLogs() {
        this.logs = new ArrayList<>();
    }

    public List<ErrorModel> getAllErrorLogs() {
        return logs;
    }

    public ErrorModel getLogById(Long id) {
        ErrorModel foundedLog = null;

        for (ErrorModel log : logs) {
            if (log.getId().equals(id)) {
                foundedLog = log;
                break;
            }
        }

        return foundedLog;
    }



    public HashMap<Long, String> searchErrorInLogs(String error) {
        // Long -> LogId, String -> Error
        HashMap<Long, String> foundedErrorsInLogs = new HashMap<>();

        for (ErrorModel log : logs) {
            if (log.getError().contains(error))
            {
                foundedErrorsInLogs.put(log.getId(), log.getError());
            }
        }

        return foundedErrorsInLogs;

    }

    public void addErrorToLogs(ErrorModel error) {
        logs.add(error);
    }

    public void deleteError(Long id) {
        logs.removeIf(founedId -> founedId.equals(id));
    }

    public void clearAllErrorOfLogs() {
        logs.clear();
    }
}
