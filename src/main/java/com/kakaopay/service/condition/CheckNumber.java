package com.kakaopay.service.condition;

import com.kakaopay.entity.UserActionLog;

import java.util.List;
import java.util.function.Predicate;

public class CheckNumber implements CheckCondition<List<UserActionLog>>{

    private int number;

    public CheckNumber(int number){
        this.number = number;
    }

    @Override
    public Predicate<List<UserActionLog>> getCheckCondition() {
        return (List<UserActionLog> userActionLogs) -> userActionLogs.size() >= number;
    }
}
