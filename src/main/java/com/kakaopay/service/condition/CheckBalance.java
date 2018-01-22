package com.kakaopay.service.condition;

import com.kakaopay.entity.MoneyReceivingLog;
import com.kakaopay.entity.TransferLog;
import com.kakaopay.entity.UserActionLog;

import java.util.List;
import java.util.function.Predicate;

public class CheckBalance implements CheckCondition<List<UserActionLog>>{

    private int balance;
    public CheckBalance(int balance){
        this.balance = balance;
    }

    @Override
    public Predicate<List<UserActionLog>> getCheckCondition() {
        return (List<UserActionLog> userActionLogs) -> {
            for (UserActionLog userActionLog : userActionLogs) {
                TransferLog transferLog = userActionLog.getTransferLog();
                if(transferLog != null && transferLog.getBalanceBeforeTransfer() - transferLog.getTransferMoney() <= balance )
                    return true;
                MoneyReceivingLog moneyReceivingLog = userActionLog.getMoneyReceivingLog();
                if(moneyReceivingLog != null && moneyReceivingLog.getBalanceBeforeReceiving() + moneyReceivingLog.getReceivingMoney() <= balance )
                    return true;
            }
            return false;
        };
    }
}
