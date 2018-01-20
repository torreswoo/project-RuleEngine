package com.kakaopay.service.condition;

import com.kakaopay.entity.UserActionLog;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class OpenAccountInTimeCondition implements Condition{

    private int searchingTimeByhour;

    public OpenAccountInTimeCondition(int searchingTimeByhour){
        this.searchingTimeByhour = searchingTimeByhour;
    }

    @Override
    public List<UserActionLog> applyCondition(List<UserActionLog> userActionLogList) {

        // condition : 계좌개설하고 searchingTimeByhour 시간이내에사용자로그
        List<UserActionLog> dateLog = userActionLogList
            .stream()
            .filter(userActionLog -> userActionLog.getServiceAccountLog() != null)
            .collect(Collectors.toList());
        Date startDate = dateLog.get(0).getActionTime();
        Date endDate = new Date(dateLog.get(0).getActionTime().getTime() + (1000 * 60 * 60 * this.searchingTimeByhour));

        List<UserActionLog> filteredLog01 = userActionLogList
            .stream()
            .filter(userActionLog ->
                userActionLog.getActionTime().compareTo(startDate) >= 0 &&
                    userActionLog.getActionTime().compareTo(endDate) <= 0)
            .collect(Collectors.toList());
        log.info("--- open account | user actionlog count: {} ({} ~ {})", filteredLog01.size(), startDate, endDate);

        return filteredLog01;
    }
}
