package com.kakaopay.service.condition;

import com.kakaopay.entity.UserActionLog;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ChargingMoneyCondition implements Condition{

    private int chargingMoney;

    public ChargingMoneyCondition(int chargingMoney){
        this.chargingMoney = chargingMoney;
    }

    @Override
    public List<UserActionLog> applyCondition(List<UserActionLog> userActionLogList) {

        List<UserActionLog> filteredLog = new ArrayList<UserActionLog>();
        int index = 0;
        for (int i = 0; i < userActionLogList.size(); i++) {
            if ( userActionLogList.get(i).getMoneyChargingLog() != null &&
                    userActionLogList.get(i).getMoneyChargingLog().getChargingMoney() >= this.chargingMoney){
                filteredLog = userActionLogList.subList(i, userActionLogList.size());
                break;
            }
        }
        log.info("--- charging money | user actionlog count: {} (chargingMoney {})", filteredLog.size(), this.chargingMoney);
        return filteredLog;
    }
}
