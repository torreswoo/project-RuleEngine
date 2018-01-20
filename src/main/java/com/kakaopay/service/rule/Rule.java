package com.kakaopay.service.rule;

import com.kakaopay.entity.UserActionLog;
import com.kakaopay.service.condition.Condition;

import java.util.List;
import java.util.function.Predicate;

public interface Rule {

    boolean checkFDS(List<UserActionLog> userActionLogList);

    void addCheckCondition(Predicate<List<UserActionLog>> checkCondition);
    void addCondition(Condition condition);
    List<Condition> getConditionList();

    String getRuleName();
    void setRuleName(String ruleName);

}
