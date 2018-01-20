package com.kakaopay.service.condition;

import com.kakaopay.entity.UserActionLog;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class RequesDateInTimeCondition implements Condition{

    private int searchingTimeByhour;
    private Date requestTime;

    public RequesDateInTimeCondition(int searchingTimeByhour, Date requestTime){
        this.searchingTimeByhour = searchingTimeByhour;
        this.requestTime = requestTime;
    }

    @Override
    public List<UserActionLog> applyCondition(List<UserActionLog> userActionLogList) {

        // condition : requestTime부터 searchingTimeByhour시간이내
        Date startDate = this.requestTime;
        Date endDate = new Date(startDate.getTime() + (1000 * 60 * 60 * this.searchingTimeByhour));

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
