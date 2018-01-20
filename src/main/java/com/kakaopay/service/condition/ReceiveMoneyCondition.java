package com.kakaopay.service.condition;

import com.kakaopay.entity.UserActionLog;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ReceiveMoneyCondition implements Condition {

    private int receivingMoney;

    public ReceiveMoneyCondition(int receivingMoney){
        this.receivingMoney = receivingMoney;
    }

    @Override
    public List<UserActionLog> applyCondition(List<UserActionLog> userActionLogList) {

        // condition : receivingMoney만원 금액 받음
        List<UserActionLog> filteredLog = userActionLogList
            .stream()
            .filter(userActionLog ->
                userActionLog.getMoneyReceivingLog() != null &&
                    userActionLog.getMoneyReceivingLog().getReceivingMoney() >= this.receivingMoney)
            .collect(Collectors.toList());
        log.info("--- receive money | user actionlog count: {} (receivingMoney {})", filteredLog.size(), this.receivingMoney);
        return filteredLog;
    }


}
